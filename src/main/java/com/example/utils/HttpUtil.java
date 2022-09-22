//package com.example.utils;
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.http.*;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.client.entity.GzipDecompressingEntity;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.methods.HttpUriRequest;
//import org.apache.http.conn.ConnectTimeoutException;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.util.EntityUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.client.HttpStatusCodeException;
//
//import java.io.*;
//import java.net.HttpURLConnection;
//import java.net.SocketTimeoutException;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//
//public class HttpUtil {
//
//    public static final String UTF8 = "UTF-8";
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);
//
//    private static final int DEFAULT_SOCKET_TIMEOUT = 5000;
//
//    private static final int DEFAULT_CONNECT_TIMEOUT = 1000;
//
//    private static final long DEFAULT_CONNECT_MANAGER_TIMEOUT = 1000;
//
//    private static final int SHORT_SOCKET_TIMEOUT = 1000;
//
//    private static final int SHORT_CONNECT_TIMEOUT = 1000;
//
//    private static final long SHORT_CONNECT_MANAGER_TIMEOUT = 1000;
//
//    private static final int LONG_SOCKET_TIMEOUT = 150000;
//
//    private static final int LONG_CONNECT_TIMEOUT = 60000;
//
//    private static final long LONG_CONNECT_MANAGER_TIMEOUT = 60000;
//
//    private static final int DEFAULT_TIMEOUT = 30000;
//
//    private HttpUtil() {
//
//
//    }
//
//    public static boolean sendJsonData(String url, String json, String successCode) {
//        String httpResult = sendJsonData(url, json);
//        if (httpResult.contains(successCode)) {
//            return true;
//        }
//        return false;
//    }
//
//    public static String sendJsonData(String url, String json) {
//        String httpResult = "";
//        try {
//            InputStream httpResultIS = HttpUtil.post(url, json, HttpUtil.UTF8, TimeoutType.DEFAULT, "application/json", "", "");
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpResultIS));
//            String line = null;
//            while ((line = bufferedReader.readLine()) != null) {
//                httpResult += line;
//            }
//            bufferedReader.close();
//            httpResultIS.close();
//            LOGGER.debug("send data not success...url=[{}], param=[{}], httpResult=[{}]", url, json, httpResult);
//        }
//        catch (HttpStatusCodeException e) {
//            LOGGER.error("send data statusCode error...url=[{}], param=[{}], httpResult=[{}], HttpStatusCodeException:", url, json, httpResult, e);
//        }
//        catch (IOException e) {
//            LOGGER.error("send data IO error...url=[{}], param=[{}], httpResult=[{}], IOException:", url, json, httpResult, e);
//        }
//        return httpResult;
//    }
//
//    public static String sendJsonData(String url, String json, String appName, String module) {
//        String httpResult = "";
//        InputStream httpResultIS = null;
//        BufferedReader bufferedReader = null;
//        int httpStatus = 200; // http请求状态
//        long start = System.nanoTime();
//        try {
//            httpResultIS = HttpUtil.post(url, json, HttpUtil.UTF8, TimeoutType.DEFAULT, "application/json", appName, module);
//            bufferedReader = new BufferedReader(new InputStreamReader(httpResultIS));
//            String line = null;
//            while ((line = bufferedReader.readLine()) != null) {
//                httpResult += line;
//            }
//            LOGGER.debug("send data success...url=[{}], param=[{}], httpResult=[{}]", url, json, httpResult);
//        }catch(SocketTimeoutException e){
//            httpStatus = 500;
//            LOGGER.error("{}_SocketTimeoutException : error...", appName, e);
//        }
//        catch(ConnectTimeoutException e){
//            httpStatus = 500;
//            LOGGER.error("{}_ConnectTimeoutException : error...", appName, e);
//        }
//        catch (HttpStatusCodeException e) {
//            httpStatus = 500;
//            LOGGER.error("{}_HttpStatusCodeException : error...", appName, e);
//        }
//        catch (IOException e) {
//            httpStatus = 500;
//            LOGGER.error("{}_IOException : error...", appName, e);
//        }
//        catch (Exception e) {
//            httpStatus = 500;
//            LOGGER.error("{}_Exception : error...", appName, e);
//        }finally{
//        	try{
//        		if(bufferedReader != null){
//        			bufferedReader.close();
//        		}
//        		if(httpResultIS != null){
//        			httpResultIS.close();
//        		}
//        	}catch(Exception e){
//        		LOGGER.error("close stream error.",e);
//        	}
//
//        }
//        long end = System.nanoTime();
//        return httpResult;
//    }
//
//    /**
//     * post data to a given url.
//     *
//     * @param url
//     * @param nameValuePairs
//     * @return
//     * @throws
//     * @throws IOException
//     */
//    public static String post(final String url, Map<String, String> nameValuePairs)
//            throws HttpStatusCodeException, IOException {
//        return post(url, nameValuePairs, UTF8);
//    }
//
//    /**
//     * post data to a given url.
//     *
//     * @param url
//     * @param nameValuePairs
//     * @return
//     * @throws
//     * @throws IOException
//     */
//    public static String post(final String url, Map<String, String> nameValuePairs, final String charset)
//            throws HttpStatusCodeException, IOException {
//        return post(url, nameValuePairs, charset, TimeoutType.DEFAULT);
//    }
//
//    public static String post(final String url, Map<String, String> nameValuePairs, final String charset, TimeoutType type) throws HttpStatusCodeException, IOException {
//        HttpClient httpclient = null;
//        if (TimeoutType.SHORT.equals(type)) {
//            httpclient = createShortTimeoutHttpClient(charset);
//        }
//        else if (TimeoutType.LONG.equals(type)) {
//            httpclient = createLongTimeoutHttpClient(charset);
//        }
//        else {
//            httpclient = createDefaultHttpClient(charset);
//        }
//        return post(url, nameValuePairs, charset, httpclient);
//    }
//
//    public static String post(final String url, Map<String, String> nameValuePairs, final String charset, final HttpClient httpclient)
//        throws HttpStatusCodeException, IOException {
//        if (LOGGER.isInfoEnabled()) {
//            LOGGER.info("url:{}" , url);
//            for (Entry<String, String> e : nameValuePairs.entrySet()) {
//                LOGGER.info("params key:{},value:{}" ,e.getKey(), e.getValue());
//            }
//        }
//        HttpPost httpost = new HttpPost(url);
//        try {
//            if (nameValuePairs.size() > 0) {
//                List<NameValuePair> nvps = new ArrayList<NameValuePair>(nameValuePairs.size());
//                for (Entry<String, String> e : nameValuePairs.entrySet()) {
//                    nvps.add(new BasicNameValuePair(e.getKey(), e.getValue()));
//                }
//
//                httpost.setEntity(new UrlEncodedFormEntity(nvps, charset));
//            }
//
//            return execute(httpclient, httpost, charset);
//        }
//        catch (UnsupportedEncodingException e) {
//            throw new IOException("in post UnsupportedEncodingException", e);
//        }
//    }
//
//    /**
//     * get data from a given url. if error return "";
//     *
//     * @param url
//     * @return
//     * @throws
//     * @throws IOException
//     */
//    public static String getData(final String url) {
//        try {
//            return get(url);
//        }
//        catch (HttpStatusCodeException e) {
//            LOGGER.error("occur exception in get{}",url,e);
//        }
//        catch (IOException e) {
//            LOGGER.error("occur exception in get{}",url,e);
//        }
//        catch (Exception e) {
//            LOGGER.error("occur exception in get{}",url,e);
//        }
//        return "";
//    }
//
//    /**
//     * get data from a given url.
//     *
//     * @param url
//     * @return
//     * @throws
//     * @throws IOException
//     */
//    public static String get(final String url)
//        throws HttpStatusCodeException, IOException {
//        return get(url, UTF8, TimeoutType.DEFAULT);
//    }
//
//    /**
//     * get data from a given url.
//     *
//     * @param url
//     * @param charset
//     * @return
//     * @throws
//     * @throws IOException
//     */
//    public static String get(final String url, final String charset)
//        throws HttpStatusCodeException, IOException {
//        return get(url, charset, TimeoutType.DEFAULT);
//    }
//
//    public static String get(final String url, final String charset, TimeoutType type)
//        throws HttpStatusCodeException, IOException {
//        HttpClient httpclient = null;
//        if (TimeoutType.SHORT.equals(type)) {
//            httpclient = createShortTimeoutHttpClient(charset);
//        }
//        else if (TimeoutType.LONG.equals(type)) {
//            httpclient = createLongTimeoutHttpClient(charset);
//        }
//        else {
//            httpclient = createDefaultHttpClient(charset);
//        }
//        return get(url, charset, httpclient);
//    }
//
//    public static String get(final String url, final String charset, final HttpClient httpclient)
//        throws HttpStatusCodeException, IOException {
//        return execute(httpclient, new HttpGet(url), charset);
//    }
//
//    private static String execute(final HttpClient httpclient, final HttpUriRequest request, final String charset)
//        throws HttpStatusCodeException, IOException {
//        if (LOGGER.isDebugEnabled()) {
//
//        }
//
//        HttpResponse response = null;
//        try {
//            response = httpclient.execute(request);
//            HttpEntity entity = response.getEntity();
//
//            if (entity != null) {
//                int statusCode = response.getStatusLine().getStatusCode();
//
//                String body = EntityUtils.toString(entity, charset);
//
//                if (statusCode == HttpStatus.SC_OK) {
//                    return body;
//                }
//                else {
//                    throw new HttpStatusCodeException(statusCode, body);
//                }
//            }
//            else {
//                throw new IOException("get null content for this request");
//            }
//        }
//        catch (ClientProtocolException e) {
//            LOGGER.error("ClientProtocolException:{}", request.getProtocolVersion(),e);
//            throw e;
//        }
//        finally {
//            // httpclient.getConnectionManager().shutdown();
//
//            if (response != null) {
//                EntityUtils.consume(response.getEntity()); // 会自动释放连接
//            }
//        }
//    }
//
//    private static HttpClient createDefaultHttpClient(final String charset) {
//        return createHttpClient(charset, DEFAULT_SOCKET_TIMEOUT, DEFAULT_CONNECT_TIMEOUT, DEFAULT_CONNECT_MANAGER_TIMEOUT);
//    }
//
//    private static HttpClient createLongTimeoutHttpClient(final String charset) {
//        return createHttpClient(charset, LONG_SOCKET_TIMEOUT, LONG_CONNECT_TIMEOUT, LONG_CONNECT_MANAGER_TIMEOUT);
//    }
//
//    private static HttpClient createShortTimeoutHttpClient(final String charset) {
//        return createHttpClient(charset, SHORT_SOCKET_TIMEOUT, SHORT_CONNECT_TIMEOUT, SHORT_CONNECT_MANAGER_TIMEOUT);
//    }
//
//    public static HttpClient createHttpClient(int socketTimeout, int connTimeout) {
//        return createHttpClient(UTF8, socketTimeout, connTimeout, DEFAULT_CONNECT_MANAGER_TIMEOUT, false);
//    }
//
//    public static HttpClient createHttpClient(final String charset, int socketTimeout, int connTimeout, long connManagerTimeout) {
//        return createHttpClient(charset, socketTimeout, connTimeout, connManagerTimeout, false);
//    }
//
//    public static HttpClient createHttpClient(final String charset, int socketTimeout, int connTimeout, long connManagerTimeout, boolean isGZIP) {
//        // 新的生成方式
//        DefaultHttpClient httpclient = HttpUtilFacade.getHttpClient();
//
//        // do gzip
//        if (isGZIP) {
//            httpclient = GZIPHttpClient(httpclient);
//        }
//
//        return httpclient;
//    }
//
//    public static InputStream post(final String url, final String xmlValueParam, final String charset, String appName, String module)
//        throws HttpStatusCodeException, IOException {
//        return post(url, xmlValueParam, charset, TimeoutType.DEFAULT, "text/xml", appName, module);
//    }
//
//    public static InputStream post(final String url, final String valueParam, final String charset, TimeoutType type, String contentType, String appName, String module)
//        throws HttpStatusCodeException, IOException {
//        if (LOGGER.isInfoEnabled()) {
//            LOGGER.info("url:{},valueParam{}" , url,valueParam);
//        }
//        HttpPost httpost = new HttpPost(url);
//        StringEntity reqEntity = new StringEntity(valueParam, charset);
//        httpost.addHeader("Content-type", contentType);
//        httpost.setEntity(reqEntity);
//
//        if (TimeoutType.SHORT.equals(type)) {
//            return executePost(createShortTimeoutHttpClient(charset), httpost, charset, appName, module);
//        }
//        else if (TimeoutType.LONG.equals(type)) {
//            return executePost(createLongTimeoutHttpClient(charset), httpost, charset, appName, module);
//        }
//        else {
//            return executePost(createDefaultHttpClient(charset), httpost, charset, appName, module);
//        }
//    }
//
//    private static InputStream executePost(HttpClient httpclient, final HttpPost httpost, final String charset, String appName, String module)
//        throws HttpStatusCodeException, IOException {
//
//        InputStream paramIs = httpost.getEntity().getContent();
//        String httpParam = "";
//        BufferedReader bufferedParamReader = new BufferedReader(new InputStreamReader(paramIs));
//        String line = null;
//        while ((line = bufferedParamReader.readLine()) != null) {
//            httpParam += line;
//        }
//        bufferedParamReader.close();
//
//        LOGGER.debug("http request with reqUrl {}, param {}", httpost.getURI(), httpParam);
//
//        long start = System.currentTimeMillis();
//
//        HttpResponse response = httpclient.execute(httpost);
//
//        HttpEntity entity = response.getEntity();
//
//        if (entity != null) {
//            int statusCode = response.getStatusLine().getStatusCode();
//
//            InputStream body = entity.getContent();
//
//            if (statusCode == HttpStatus.SC_OK) {
//
//                /*String httpResult = "";
//                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(body));
//                line = null;
//                while ((line = bufferedReader.readLine()) != null) {
//                    httpResult += line;
//                }
//                bufferedReader.close();*/
//
//                LOGGER.debug("http request {} {} consuming {} msec result {}", appName, module, System.currentTimeMillis() - start, "SUCCESS");
//
//
//                return body;
//            }
//            else {
//                throw new HttpStatusCodeException(statusCode, EntityUtils.toString(entity, charset));
//            }
//        }
//        else {
//            throw new IOException("get null content for this post");
//        }
//    }
//
//    private static DefaultHttpClient GZIPHttpClient(DefaultHttpClient httpclient) {
//        httpclient.addRequestInterceptor((request, context) -> {
//            if (!request.containsHeader("Accept-Encoding")) {
//                request.addHeader("Accept-Encoding", "gzip");
//            }
//        });
//
//        httpclient.addResponseInterceptor((response, context) -> {
//            HttpEntity entity = response.getEntity();
//            Header ceheader = entity.getContentEncoding();
//            if (ceheader != null) {
//                HeaderElement[] codecs = ceheader.getElements();
//                for (int i = 0; i < codecs.length; i++) {
//                    if (codecs[i].getName().equalsIgnoreCase("gzip")) {
//                        response.setEntity(new GzipDecompressingEntity(response.getEntity()));
//                        return;
//                    }
//                }
//            }
//        });
//        return httpclient;
//    }
//
//    public static boolean isConnect(String urlStr) {
//        Boolean isValid = false;
//        if(StringUtils.isNotBlank(urlStr)){
//            HttpURLConnection con = null;
//            try {
//                URL url = new URL(urlStr);
//                con = (HttpURLConnection) url.openConnection();
//                con.setConnectTimeout(DEFAULT_TIMEOUT);
//                con.setReadTimeout(DEFAULT_TIMEOUT);
//                int state = con.getResponseCode();
//                if (state == 200) {
//                    isValid = true;
//                }
//            }catch (Exception ex) {
//                isValid = false;
//            }finally {
//                if(con!=null){
//                    con.disconnect();
//                }
//            }
//        }
//        return isValid;
//    }
//
//    public enum TimeoutType implements IEnumDisplay {
//        SHORT("short"), LONG("long"), DEFAULT("default");
//        private String msg;
//
//        private TimeoutType(String msg) {
//            this.msg = msg;
//        }
//
//        @Override
//        public String getDisplayName() {
//            return this.msg;
//        }
//    }
//
//    private static final CloseableHttpClient httpClient;
//    public static final String CHARSET = "UTF-8";
//    static {
//        RequestConfig config = RequestConfig.custom().setConnectTimeout(60000).setSocketTimeout(150000).build();
//        httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
//    }
//
//    public static String sendPost(String url, Map<String, Object> params) {
//        if(StringUtils.isEmpty(url)){
//            return "";
//        }
//        try {
//            List<NameValuePair> pairs = null;
//            if (params != null && !params.isEmpty()) {
//                pairs = new ArrayList<NameValuePair>(params.size());
//                for (String key : params.keySet()) {
//                    pairs.add(new BasicNameValuePair(key, params.get(key).toString()));
//                }
//            }
//            HttpPost httpPost = new HttpPost(url);
//            if (pairs != null && pairs.size() > 0) {
//                httpPost.setEntity(new UrlEncodedFormEntity(pairs, CHARSET));
//            }
//            CloseableHttpResponse response = httpClient.execute(httpPost);
//            int statusCode = response.getStatusLine().getStatusCode();
//            if (statusCode != 200) {
//                httpPost.abort();
//                throw new RuntimeException("HttpClient,error status code :" + statusCode);
//            }
//            HttpEntity entity = response.getEntity();
//            String result = null;
//            if (entity != null) {
//                result = EntityUtils.toString(entity, "utf-8");
//            }
//            EntityUtils.consume(entity);
//            response.close();
//            return result;
//        } catch (Exception e) {
//            LOGGER.error("sendPost error!url={},exception:",url,e);
//        }
//        return null;
//    }
//}
