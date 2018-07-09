package com.imagestudio.util;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.imagestudio.data.Media;
import com.imagestudio.data.MediaHelper;
import com.imagestudio.progress.ProgressBottomSheet;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import kotlin.jvm.internal.Intrinsics;

public class MediaUtils {

    public static final void shareMedia(@NotNull Context context, @NotNull List mediaList) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(mediaList, "mediaList");
        Intent intent = new Intent("android.intent.action.SEND_MULTIPLE");
        HashMap types = new HashMap();
        ArrayList files = new ArrayList();
        Iterator var6 = mediaList.iterator();

        String type;
        Object var10000;
        while(var6.hasNext()) {
            Media f = (Media)var6.next();
            type = MimeTypeUtils.getTypeMime(f.getMimeType());
            int count = 0;
            if (types.containsKey(type)) {
                var10000 = types.get(type);
                if (var10000 == null) {
                    Intrinsics.throwNpe();
                }

                count = ((Number)var10000).intValue();
            }

            Map var9 = (Map)types;
            Intrinsics.checkExpressionValueIsNotNull(type, "mimeType");
            Integer var11 = count;
            var9.put(type, var11);
            files.add(LegacyCompatFileProvider.getUri(context, f.getFile()));
        }

        Set fileTypes = types.keySet();
        if (fileTypes.size() > 1) {
            Toast.makeText(context, 2131624442, 0).show();
        }

        int max = -1;
        type = (String)null;
        Iterator var15 = fileTypes.iterator();

        while(var15.hasNext()) {
            String fileType = (String)var15.next();
            var10000 = types.get(fileType);
            if (var10000 == null) {
                Intrinsics.throwNpe();
            }

            Integer count = (Integer)var10000;
            if (Intrinsics.compare(count, max) > 0) {
                type = fileType;
            }
        }

        StringBuilder var10001 = new StringBuilder();
        if (type == null) {
            Intrinsics.throwNpe();
        }

        intent.setType(var10001.append(type).append("/*").toString());
        intent.putParcelableArrayListExtra("android.intent.extra.STREAM", files);
        intent.setFlags(1);
        context.startActivity(Intent.createChooser(intent, (CharSequence)context.getString(2131624373)));
    }

    public static final void deleteMedia(@NotNull Context context, @NotNull List mediaList, @NotNull FragmentManager fragmentManager, @NotNull ProgressBottomSheet.Listener deleteListener) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(mediaList, "mediaList");
        Intrinsics.checkParameterIsNotNull(fragmentManager, "fragmentManager");
        Intrinsics.checkParameterIsNotNull(deleteListener, "deleteListener");
        ArrayList sources = new ArrayList(mediaList.size());
        Iterator var6 = mediaList.iterator();

        while(var6.hasNext()) {
            Media media = (Media)var6.next();
            sources.add(MediaHelper.deleteMedia(context.getApplicationContext(), media));
        }

        ProgressBottomSheet bottomSheet = (new ProgressBottomSheet.Builder(2131624083)).autoDismiss(false).sources((List)sources).listener(deleteListener).build();
        bottomSheet.showNow(fragmentManager, (String)null);
    }

}
