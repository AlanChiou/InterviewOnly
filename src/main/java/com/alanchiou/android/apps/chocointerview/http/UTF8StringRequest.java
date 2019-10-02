package com.alanchiou.android.apps.chocointerview.http;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import java.nio.charset.StandardCharsets;
import javax.annotation.Nullable;

final class UTF8StringRequest extends StringRequest {

  UTF8StringRequest(int method, String url,
      Listener<String> listener,
      @Nullable ErrorListener errorListener) {
    super(method, url, listener, errorListener);
  }

  @Override
  protected Response<String> parseNetworkResponse(NetworkResponse response) {
    return Response.success(new String(response.data, StandardCharsets.UTF_8),
        HttpHeaderParser.parseCacheHeaders(response));
  }
}
