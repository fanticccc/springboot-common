package com.example.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.params.SyncBasicHttpParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpUtilFacade {
    private static HttpParams httpParams;

    private static PoolingClientConnectionManager conMgr;

    static {
        HttpParams params = new SyncBasicHttpParams();
        params.setParameter(HttpConnectionParams.SO_TIMEOUT, 5000);
        params.setParameter(HttpConnectionParams.CONNECTION_TIMEOUT, 1000);
        params.setParameter(ClientPNames.CONN_MANAGER_TIMEOUT, 1000);

        HttpProtocolParams.setContentCharset(params, "UTF-8");

        conMgr = new PoolingClientConnectionManager();
        conMgr.setMaxTotal(1000);
        conMgr.setDefaultMaxPerRoute(1000);
    }

    public static DefaultHttpClient getHttpClient() {
        return new DefaultHttpClient(conMgr, httpParams);
    }

    public String execute(String url)
        throws HttpStatusCodeException, IOException {

        HttpClient httpClient = getHttpClient();

        HttpUriRequest request = null;
        HttpResponse response = null;
        HttpEntity entity = null;

        try {
            request = new HttpGet(url);
            response = httpClient.execute(request);
            entity = response.getEntity();

            if (entity != null) {
                int statusCode = response.getStatusLine().getStatusCode();

                String body = EntityUtils.toString(entity, "UTF-8");

                if (statusCode == HttpStatus.SC_OK) {
                    return body;
                }
                else {
                    throw new HttpStatusCodeException(statusCode, body);
                }
            }
            else {
                throw new IOException("get null content for this request");
            }
        }
        catch (ClientProtocolException e) {
            throw e;
        }
        finally {
            // httpClient.getConnectionManager().shutdown();

            if (response != null) {
                EntityUtils.consume(response.getEntity()); // 会自动释放连接
            }
        }
    }
}
