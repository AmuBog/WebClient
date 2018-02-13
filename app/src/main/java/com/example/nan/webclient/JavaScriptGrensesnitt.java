package com.example.nan.webclient;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.widget.TextView;

import static android.Manifest.permission.READ_CONTACTS;

public class JavaScriptGrensesnitt extends Activity{

    android.content.Context kontekst;
    private static final int REQUEST_READ_CONTACTS = 444;

    String bruker = "";

    public JavaScriptGrensesnitt(android.content.Context kontekst) {
        super();
        this.kontekst = kontekst;
    }

    @android.webkit.JavascriptInterface   // ved target >= 17 må dette være med
    public void getContacts(String mail) {

        if (!mayRequestContacts()) {
            return;
        }

        //declarations
        Cursor cursor;
        //TextView text;

        String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
        Uri EmailCONTENT_URI = ContactsContract.CommonDataKinds.Email.CONTENT_URI;
        String ADDRESS = ContactsContract.CommonDataKinds.Email.ADDRESS;

        ContentResolver contentResolver = getContentResolver();
        cursor = contentResolver.query(EmailCONTENT_URI, null, ADDRESS + " = '" + mail +"'", null, null);
        cursor.moveToNext();
        //text = findViewById(R.id.textView3);
        String name = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME));
        //text.append(name);

    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getContacts(bruker);
            }
        }
    }
}