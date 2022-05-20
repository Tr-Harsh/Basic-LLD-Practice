package com.outleap.scholar.httpclient.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.outleap.scholar.ai_evaluation.IELTS8ApiDefinition;
import com.outleap.scholar.ameyo.AmeyoServiceApiDefinition;
import com.outleap.scholar.chat.sendbird.SendBirdApiDefinition;
import com.outleap.scholar.calendar.CalendarApiDefinition;
import com.outleap.scholar.coursefinder.CourseFinderApiDefinition;
import com.outleap.scholar.crm.zoho.api.ZohoApiDefinition;
import com.outleap.scholar.crm.zoho.api.ZohoTokenApiDefinition;
import com.outleap.scholar.firebase.FirebaseApiDefinition;
import com.outleap.scholar.gb_aggregator.GbAggregatorApiDefinition;
import com.outleap.scholar.httpclient.AuthApiDefinition;
import com.outleap.scholar.httpclient.CommunicationApiDefinition;
import com.outleap.scholar.httpclient.ZoomApiDefinition;
import com.outleap.scholar.lsq.api.LsqApiDefinition;
import com.outleap.scholar.meeting.agora.AgoraApiDefinition;
import com.outleap.scholar.mocktests.tcyonline.TCYOnlineApiDefinition;
import com.outleap.scholar.reminder.client.ReminderServiceApiDefinition;
import com.outleap.scholar.shared.core.service.SystemPropertyManager;
import com.outleap.scholar.slack.SlackApiDefinition;
import com.outleap.scholar.strapi.StrapiApiDefinition;
import com.outleap.scholar.studyzone.collegify.CollegifyApiDefinition;
import com.outleap.scholar.task.client.TaskApiDefinition;
import com.outleap.scholar.vimeo.VimeoApiDefinition;
import com.outleap.scholar.viralloops.ViralLoopsApiDefinition;
import com.outleap.scholar.webengage.WebEngageApiDefinition;
import kotlin.jvm.JvmClassMappingKt;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okio.Buffer;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
public class RetrofitConfiguration {
    @Bean
    @Qualifier("default")
    public OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        String headers = ObjectUtils.isNotEmpty(request.headers()) ? request.headers().toString() : "";
                        RequestBody requestBody = request.body();
                        Buffer requestBuffer = new Buffer();
                        if (ObjectUtils.isNotEmpty(requestBody)
                                && !(requestBody instanceof MultipartBody)) {
                            requestBody.writeTo(requestBuffer);
                        }
                        log.info("RETROFIT:REQUEST : \n METHOD : {} \n URL : {} \n HEADERS : {} \n BODY : {}",
                                request.method(), request.url(), headers, requestBuffer.readUtf8());
                        return chain.proceed(request);
                    }
                })
                .build();
    }

    @Bean
    @Qualifier("noPrint")
    public OkHttpClient getOkHttpClientNoPrint() {
        return new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();
    }

    @Bean
    @Qualifier("leadsquared")
    public Retrofit getRetrofitLsq(
            Environment env,
            @Qualifier("default") OkHttpClient okHttpClient
    ) {
        String lsq_api_url = env.getProperty("LSQ_API_URL");
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .excludeFieldsWithoutExposeAnnotation()
                                .disableHtmlEscaping()
                                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                                .create()
                ))
                .baseUrl(lsq_api_url)
                .client(okHttpClient)
                .build();
    }

    @Bean
    @Qualifier("leadsquaredFiles")
    public Retrofit getRetrofitLsqFiles(Environment env,
                                        @Qualifier("noPrint") OkHttpClient okHttpClient) {
        String lsq_api_url = env.getProperty("LSQ_FILES_API_URL");
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .excludeFieldsWithoutExposeAnnotation()
                                .disableHtmlEscaping()
                                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                                .create()
                ))
                .baseUrl(lsq_api_url)
                .client(okHttpClient)
                .build();
    }

    @Bean
    @Qualifier("communication")
    public Retrofit getRetrofitCommunication(
            Environment env,
            @Qualifier("noPrint") OkHttpClient okHttpClient
    ) {
        String comm_api_url = env.getProperty("COMM_API_URL");
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .disableHtmlEscaping()
                                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                                .create()
                ))
                .baseUrl(comm_api_url)
                .client(okHttpClient)
                .build();
    }

    @Bean
    @Qualifier("auth")
    public Retrofit getRetrofitAuth(
            Environment env,
            @Qualifier("default") OkHttpClient okHttpClient
    ) {
        String auth_api_url = env.getProperty("AUTH_API_URL");
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .disableHtmlEscaping()
                                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                                .create()
                ))
                .baseUrl(auth_api_url)
                .client(okHttpClient)
                .build();
    }

    @Bean
    @Qualifier("zoom")
    public Retrofit getRetrofitZoom(
            Environment env,
            @Qualifier("default") OkHttpClient okHttpClient
    ) {
        String api_url = "https://api.zoom.us/v2/";
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .disableHtmlEscaping()
                                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                                .create()
                ))
                .baseUrl(api_url)
                .client(okHttpClient)
                .build();
    }

    @Bean
    @Qualifier("viralloops")
    public Retrofit getRetrofitViralLoops(
            Environment env,
            @Qualifier("noPrint") OkHttpClient okHttpClient
    ) {
        String base_api_url = "https://app.viral-loops.com/api/";
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .disableHtmlEscaping()
                                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                                .create()
                ))
                .baseUrl(base_api_url)
                .client(okHttpClient)
                .build();
    }

    @Bean
    @Qualifier("zohoTokenCRM")
    public Retrofit getRetrofitZohoTokenCrm(
            @Qualifier("default") OkHttpClient okHttpClient
    ) {
        String api_url = "https://accounts.zoho.in/";
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .excludeFieldsWithoutExposeAnnotation()
                                .disableHtmlEscaping()
                                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                                .create()
                ))
                .baseUrl(api_url)
                .client(okHttpClient)
                .build();
    }

    @Bean
    @Qualifier("zohoCRM")
    public Retrofit getRetrofitZohoCrm(
            Environment env,
            @Qualifier("default") OkHttpClient okHttpClient
    ) {
        String api_url = env.getProperty("ZOHO_BASE_URL");

        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .disableHtmlEscaping()
                                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                                .create()
                ))
                .baseUrl(api_url)
                .client(okHttpClient)
                .build();
    }

    @Bean
    @Qualifier("agora")
    public Retrofit getRetrofitAgora(
            Environment env,
            @Qualifier("default") OkHttpClient okHttpClient
    ) {
        String api_url = "https://api.agora.io/";
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .disableHtmlEscaping()
                                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                                .create()
                ))
                .baseUrl(api_url)
                .client(okHttpClient)
                .build();
    }

    @Bean
    @Qualifier("webengage")
    public Retrofit getRetrofitWebEngage(
            Environment env,
            @Qualifier("noPrint") OkHttpClient okHttpClient
    ) {
        String base_api_url = env.getProperty("WEB_ENGAGE_BASE_URL");

        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .disableHtmlEscaping()
                                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                                .create()
                ))
                .baseUrl(base_api_url)
                .client(okHttpClient)
                .build();
    }

    @Bean
    @Qualifier("webengageBusiness")
    public Retrofit getRetrofitbusinessWebEngage(
            Environment env,
            @Qualifier("default") OkHttpClient okHttpClient
    ) {
        String base_api_url = env.getProperty("WEB_ENGAGE_BUSINESS_BASE_URL");

        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .disableHtmlEscaping()
                                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                                .create()
                ))
                .baseUrl(base_api_url)
                .client(okHttpClient)
                .build();
    }

    @Bean
    @Qualifier("vimeo")
    public Retrofit getRetrofitVimeo(
            Environment env,
            @Qualifier("noPrint") OkHttpClient okHttpClient
    ) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .disableHtmlEscaping()
                                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                                .create()
                ))
                .baseUrl("https://api.vimeo.com")
                .client(okHttpClient)
                .build();
    }

    @Bean
    @Qualifier("slack")
    public Retrofit getRetrofitSlack(
            Environment env,
            @Qualifier("noPrint") OkHttpClient okHttpClient
    ) {
        String base_api_url = env.getProperty("SLACK_WEBHOOK_BASE_URL");

        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .disableHtmlEscaping()
                                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                                .create()
                ))
                .baseUrl(base_api_url)
                .client(okHttpClient)
                .build();
    }

    @Bean
    @Qualifier("send-bird")
    public Retrofit getRetrofitSendBird(
            Environment env,
            @Qualifier("sendbird") OkHttpClient okHttpClient
    ) {
        String base_api_url = env.getProperty("SEND_BIRD_APP_URL");
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .disableHtmlEscaping()
                                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                                .create()
                ))


                .baseUrl(base_api_url)
                .client(okHttpClient)
                .build();
    }

    @Qualifier("calendar")
    @Bean
    public Retrofit getRetrofitCalendar(
            Environment env,
            @Qualifier("noPrint") OkHttpClient okHttpClient
    ) {
        String base_api_url = env.getProperty("CALENDAR_WEBHOOK_BASE_URL");


        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .disableHtmlEscaping()
                                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                                .create()
                ))


                .baseUrl(base_api_url)
                .client(okHttpClient)
                .build();
    }

    @Qualifier("gbAggregator")
    @Bean
    public Retrofit getRetrofitGbAggregator(
            Environment env,
            @Qualifier("noPrint") OkHttpClient okHttpClient
    ) {
        String base_api_url = env.getProperty("GB_AGGREGATOR_BASE_URL");


        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .disableHtmlEscaping()
                                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                                .create()
                ))


                .baseUrl(base_api_url)
                .client(okHttpClient)
                .build();
    }

    @Bean
    @Qualifier("ielts8")
    public Retrofit getRetrofitIELTS8(
            Environment env,
            @Qualifier("noPrint") OkHttpClient okHttpClient
    ) {
        String base_api_url = env.getProperty("IELTS8_BASE_URL");

        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .disableHtmlEscaping()
                                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                                .setLenient()
                                .create()
                ))
                .baseUrl(base_api_url)
                .client(okHttpClient)
                .build();
    }

    @Qualifier("sendbird")
    @Bean
    public OkHttpClient getOkHttpSendBirdClient(Environment env) {
        String apiToken = env.getProperty("SEND_BIRD_TOKEN");

        return new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();

                        Request newRequest;

                        newRequest = request.newBuilder()
                                .addHeader("Api-Token", apiToken)
                                .addHeader("Content-Type","application/json; charset=utf8")
                                .build();

                        final Request copy = request.newBuilder().build();
                        final Buffer buffer = new Buffer();
                        String res = "";
                        if(copy.body() != null) {
                            copy.body().writeTo(buffer);
                            res = buffer.readUtf8();
                        }
                        log.info("RETROFIT:REQUEST : \n METHOD : {} \n URL : {} \n HEADERS : {} \n BODY : {}",
                                request.method(), request.url(), request.body(), res);
                        return chain.proceed(newRequest);
                    }
                })
                .build();
    }

    @Qualifier("courseFinder")
    @Bean
    public Retrofit getRetrofitCourseFinder(
            Environment env,
            @Qualifier("noPrint") OkHttpClient okHttpClient
    ) {
        String base_api_url = env.getProperty("COURSE_FINDER_BASE_URL");


        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .disableHtmlEscaping()
                                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                                .create()
                ))


                .baseUrl(base_api_url)
                .client(okHttpClient)
                .build();
    }

    @Bean
    @Qualifier("firebase")
    public Retrofit getRetrofitFirebase(
            SystemPropertyManager systemPropertyManager,
            @Qualifier("default") OkHttpClient okHttpClient
    ) {
        String base_api_url = systemPropertyManager.getProperty("FIREBASE_BASE_URL",  JvmClassMappingKt.getKotlinClass(String.class));

        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .disableHtmlEscaping()
                                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                                .create()
                ))
                .baseUrl(base_api_url)
                .client(okHttpClient)
                .build();
    }

    @Bean
    @Qualifier("strapi")
    public Retrofit getRetrofitStrapi(
            SystemPropertyManager systemPropertyManager,
            @Qualifier("default") OkHttpClient okHttpClient
    ) {
        String base_api_url = systemPropertyManager.getProperty("STRAPI_BASE_URL",  JvmClassMappingKt.getKotlinClass(String.class));

        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .disableHtmlEscaping()
                                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                                .create()
                ))
                .baseUrl(base_api_url)
                .client(okHttpClient)
                .build();
    }

    @Bean
    @Qualifier("collegify-api")
    public Retrofit getRetrofitCollegify(
            Environment env,
            @Qualifier("collegify") OkHttpClient okHttpClient
    ) {
        String base_api_url = env.getProperty("COLLEGIFY_API_BASE_URL");
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .disableHtmlEscaping()
                                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                                .create()
                ))


                .baseUrl(base_api_url)
                .client(okHttpClient)
                .build();
    }

    @Qualifier("collegify")
    @Bean
    public OkHttpClient getOkHttpCollegifyClient(Environment env) {
        String apikey = env.getProperty("COLLEGIFY_API_TOKEN");

        return new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();

                        Request newRequest;

                        newRequest = request.newBuilder()
                                .addHeader("apikey", apikey)
                                .addHeader("Content-Type","application/json")
                                .build();

                        final Request copy = request.newBuilder().build();
                        final Buffer buffer = new Buffer();
                        String res = "";
                        if(copy.body() != null) {
                            copy.body().writeTo(buffer);
                            res = buffer.readUtf8();
                        }
                        log.info("RETROFIT:REQUEST : \n METHOD : {} \n URL : {} \n HEADERS : {} \n BODY : {}",
                                request.method(), request.url(), request.body(), res);
                        return chain.proceed(newRequest);
                    }
                })
                .build();
    }

    @Bean
    @Qualifier("reminder")
    public Retrofit getRetrofitReminder(
            Environment env,
            @Qualifier("noPrint") OkHttpClient okHttpClient
    ) {
        String reminder_service_api_url = env.getProperty("REMINDER_SERVICE_API_URL");
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .disableHtmlEscaping()
                                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                                .create()
                ))
                .baseUrl(reminder_service_api_url)
                .client(okHttpClient)
                .build();
    }

    @Bean
    @Qualifier("tcyonline-api")
    public Retrofit getRetrofitTCYOnline(
            Environment env,
            @Qualifier("tcyonline") OkHttpClient okHttpClient
    ) {
        String base_api_url = env.getProperty("TCY_ONLINE_API_BASE_URL");
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .disableHtmlEscaping()
                                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                                .create()
                ))


                .baseUrl(base_api_url)
                .client(okHttpClient)
                .build();
    }

    @Qualifier("tcyonline")
    @Bean
    public OkHttpClient getOkHttpTCYOnlineClient(Environment env) {
        return new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();

                        Request newRequest;

                        newRequest = request.newBuilder()
                                .addHeader("Content-Type","application/x-www-form-urlencoded")
                                .build();

                        final Request copy = request.newBuilder().build();
                        final Buffer buffer = new Buffer();
                        String res = "";
                        if(copy.body() != null) {
                            copy.body().writeTo(buffer);
                            res = buffer.readUtf8();
                        }
                        log.info("RETROFIT:REQUEST : \n METHOD : {} \n URL : {} \n HEADERS : {} \n BODY : {}",
                                request.method(), request.url(), request.headers(), res);
                        return chain.proceed(newRequest);
                    }
                })
                .build();
    }

    @Bean
    @Qualifier("ameyo-retrofit")
    public Retrofit getRetrofitAmeyo(
            Environment env,
            @Qualifier("ameyo-okhttp") OkHttpClient okHttpClient
    ) {
        String ameyo_service_api_url = env.getProperty("AMEYO_SERVICE_API_URL");
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .disableHtmlEscaping()
                                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                                .create()
                ))
                .baseUrl(ameyo_service_api_url)
                .client(okHttpClient)
                .build();
    }

    @Qualifier("ameyo-okhttp")
    @Bean
    public OkHttpClient getOkHttpAmeyoClient(Environment env) {
        String ameyo_service_api_hash_key = env.getProperty("AMEYO_SERVICE_API_HASH_KEY");
        return new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();

                        Request newRequest;

                        newRequest = request.newBuilder()
                                .addHeader("requesting-host","outleap")
                                .addHeader("policy-name","token-based-authorization-policy")
                                .addHeader("hash-key",ameyo_service_api_hash_key)
                                .addHeader("Content-Type","application/x-www-form-urlencoded")
                                .build();

                        final Request copy = request.newBuilder().build();
                        final Buffer buffer = new Buffer();
                        String res = "";
                        if(copy.body() != null) {
                            copy.body().writeTo(buffer);
                            res = buffer.readUtf8();
                        }
                        final Gson gson = new Gson();
                        log.info("RETROFIT:REQUEST : \n METHOD : {} \n URL : {} \n HEADERS : {} \n BODY : {} \n EncodedBody : {}",
                                newRequest.method(), newRequest.url(), newRequest.headers(), gson.toJson(newRequest.body()), res);
                        return chain.proceed(newRequest);
                    }
                })
                .build();
    }

    @Bean
    @Qualifier("task")
    public Retrofit getRetrofitTask(
            Environment env,
            @Qualifier("default") OkHttpClient okHttpClient
    ) {
        String auth_api_url = env.getProperty("TASK_BACKEND_API_URL");
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .disableHtmlEscaping()
                                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                                .create()
                ))
                .baseUrl(auth_api_url)
                .client(okHttpClient)
                .build();
    }

    @Bean
    public LsqApiDefinition getLsqApiDefinition(@Qualifier("leadsquared") Retrofit retrofit) {
        return retrofit.create(LsqApiDefinition.class);
    }

    @Bean
    public CommunicationApiDefinition getCommunicationApiDefinition(@Qualifier("communication") Retrofit retrofit) {
        return retrofit.create(CommunicationApiDefinition.class);
    }

    @Bean
    public AuthApiDefinition getAuthApiDefinition(@Qualifier("auth") Retrofit retrofit) {
        return retrofit.create(AuthApiDefinition.class);
    }

    @Bean
    public ZoomApiDefinition getZoomApiDefinition(@Qualifier("zoom") Retrofit retrofit) {
        return retrofit.create(ZoomApiDefinition.class);
    }

    @Bean
    public ZohoTokenApiDefinition getZohoTokenApiDefinition(@Qualifier("zohoTokenCRM") Retrofit retrofit) {
        return retrofit.create(ZohoTokenApiDefinition.class);
    }

    @Bean
    public ZohoApiDefinition getZohoApiDefinition(@Qualifier("zohoCRM") Retrofit retrofit) {
        return retrofit.create(ZohoApiDefinition.class);
    }

    @Bean
    public AgoraApiDefinition getAgoraApiDefinition(@Qualifier("agora") Retrofit retrofit) {
        return retrofit.create(AgoraApiDefinition.class);
    }

    @Bean
    public ViralLoopsApiDefinition getViralLoopsApiDefinition(@Qualifier("viralloops") Retrofit retrofit) {
        return retrofit.create(ViralLoopsApiDefinition.class);
    }

    @Bean
    public WebEngageApiDefinition getWebEngageApiDefinition(@Qualifier("webengage") Retrofit retrofit) {
        return retrofit.create(WebEngageApiDefinition.class);
    }

    @Bean
    public WebEngageApiDefinition getWebEngageBusinessApiDefinition(@Qualifier("webengageBusiness") Retrofit retrofit) {
        return retrofit.create(WebEngageApiDefinition.class);
    }

    @Bean
    public VimeoApiDefinition getVimeoApiDefinition(@Qualifier("vimeo") Retrofit retrofit) {
        return retrofit.create(VimeoApiDefinition.class);
    }

    @Bean
    public SlackApiDefinition getSlackApiDefinition(@Qualifier("slack") Retrofit retrofit) {
        return retrofit.create(SlackApiDefinition.class);
    }

    @Bean
    public SendBirdApiDefinition getSendBirdApiDefinition(@Qualifier("send-bird") Retrofit retrofit) {
        return retrofit.create(SendBirdApiDefinition.class);
    }

    @Bean
    public CalendarApiDefinition getCalendarApiDefinition(@Qualifier("calendar") Retrofit retrofit) {
        return retrofit.create(CalendarApiDefinition.class);
    }

    @Bean
    public FirebaseApiDefinition getFirebaseApiDefinition(@Qualifier("firebase") Retrofit retrofit) {
        return retrofit.create(FirebaseApiDefinition.class);
    }

    @Bean
    public IELTS8ApiDefinition getIELTS8APIDefinition (@Qualifier("ielts8") Retrofit retrofit) {
        return retrofit.create(IELTS8ApiDefinition.class);
    }
    @Bean
    public StrapiApiDefinition getStrapiApiDefinition(@Qualifier("strapi") Retrofit retrofit) {
        return retrofit.create(StrapiApiDefinition.class);
    }


    @Bean
    public CourseFinderApiDefinition getCourseFinderAPIDefinition (@Qualifier("courseFinder") Retrofit retrofit) {
        return retrofit.create(CourseFinderApiDefinition.class);
    }

    @Bean
    public GbAggregatorApiDefinition getGbAggregatorApiDefinition(@Qualifier("gbAggregator") Retrofit retrofit) {
        return retrofit.create(GbAggregatorApiDefinition.class);
    }

    @Bean
    public CollegifyApiDefinition getCollegifyApiDefinition(@Qualifier("collegify-api") Retrofit retrofit) {
        return retrofit.create(CollegifyApiDefinition.class);
    }

    @Bean
    public ReminderServiceApiDefinition getReminderServiceApiDefinition(@Qualifier("reminder") Retrofit retrofit) {
        return retrofit.create(ReminderServiceApiDefinition.class);
    }

    @Bean
    public TCYOnlineApiDefinition getTCYOnlineApiDefinition(@Qualifier("tcyonline-api") Retrofit retrofit) {
        return retrofit.create(TCYOnlineApiDefinition.class);
    }

    @Bean
    public TaskApiDefinition getTaskApiDefinition(@Qualifier("task") Retrofit retrofit) {
        return retrofit.create(TaskApiDefinition.class);
    }

    @Bean
    public AmeyoServiceApiDefinition getAmeyoServiceApiDefinition(@Qualifier("ameyo-retrofit") Retrofit retrofit) {
        return retrofit.create(AmeyoServiceApiDefinition.class);
    }
}
