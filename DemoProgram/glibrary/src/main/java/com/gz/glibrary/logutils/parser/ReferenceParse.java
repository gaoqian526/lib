package com.gz.glibrary.logutils.parser;

import com.gz.glibrary.logutils.Parser;
import com.gz.glibrary.logutils.utils.ObjectUtil;
import java.lang.ref.Reference;

/**
 * Created by pengwei on 16/3/22.
 */
public class ReferenceParse implements Parser<Reference> {
  @Override public Class<Reference> parseClassType() {
    return Reference.class;
  }

  @Override public String parseString(Reference reference) {
    Object actual = reference.get();
    StringBuilder builder =
        new StringBuilder(reference.getClass().getSimpleName() + "<" + actual.getClass().getSimpleName() + "> {");
    builder.append("→" + ObjectUtil.objectToString(actual));
    return builder.toString() + "}";
  }
}
