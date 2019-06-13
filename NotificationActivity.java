package com.example.admin.collegelineup;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class NotificationActivity extends AppCompatActivity {
    private Toolbar toolbarNoticeBoard;
    private TextView textview;
    private String text1,text;
    private MyFirebaseMessagingService myFirebaseMessagingService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        toolbarNoticeBoard=(Toolbar)findViewById(R.id.toolbarNoticeBoard);
        textview=(TextView)findViewById(R.id.textview);
        initToolbar();
        MyFirebaseMessagingService myFirebaseMessagingService=new MyFirebaseMessagingService();
        text1=myFirebaseMessagingService.getText();
        textview.setText(text1);
        }

    private void initToolbar() {
        setSupportActionBar(toolbarNoticeBoard);
        getSupportActionBar().setTitle("NoticeBoard");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
            {
                onBackPressed();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public class MyFirebaseMessagingService extends FirebaseMessagingService {   //
        // public String text;
        public MyFirebaseMessagingService() {
        }

        @Override
        public void onMessageReceived(RemoteMessage remoteMessage) {
            super.onMessageReceived(remoteMessage);
            sendNotification(remoteMessage.getNotification().getBody());
        }

        private void sendNotification(String messageBody) {
            text = messageBody.toString();
             getText();

            Intent intent = new Intent(this, NotificationActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
            notificationBuilder.setSmallIcon(R.drawable.ic_stat_name);
            notificationBuilder.setContentTitle("CollegeLineUp");
            notificationBuilder.setContentText(messageBody);
            notificationBuilder.setAutoCancel(true);
            notificationBuilder.setSound(defaultSoundUri);
            notificationBuilder.setContentIntent(pendingIntent);
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0, notificationBuilder.build());

        }

        public String getText() {
            return text;
        }
    }
}
