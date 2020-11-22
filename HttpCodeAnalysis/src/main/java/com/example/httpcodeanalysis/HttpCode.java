package com.example.httpcodeanalysis;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

public class HttpCode {

    private PrintWriter printWriter;

    ArrayList<CodeDescription> httpCodesStatus = new ArrayList<CodeDescription>() {
        {
            add(new CodeDescription("200", "OK"));
            add(new CodeDescription("100", "Server continue working"));
            add(new CodeDescription("101", "Server Switching Protocols"));
            add(new CodeDescription("103", "Server Early Hints"));
            add(new CodeDescription("201", "Server resources are created"));
            add(new CodeDescription("202", "Server request accepted"));
            add(new CodeDescription("203", "Server Non-Authoritative Information"));
            add(new CodeDescription("204", "The server successfully processed the request and is not returning any content"));
            add(new CodeDescription("205", "Server is no returning any content"));
            add(new CodeDescription("206", "The server is delivering only part of the resource"));
            add(new CodeDescription("207", "XML message and can contain a number"));
            add(new CodeDescription("208", "The members of a DAV binding have already been enumerated"));
            add(new CodeDescription("226", "The server has fulfilled a request for the resource"));
            add(new CodeDescription("300", "Server Multiple Choices"));
            add(new CodeDescription("301", "Server Moved Permanently"));
            add(new CodeDescription("302", "Server Found (Previously Moved temporarily"));
            add(new CodeDescription("303", "The response to the request can be found under another URI using the GET method"));
            add(new CodeDescription("304", "Server Not Modified"));
            add(new CodeDescription("305", "Server use Proxy"));
            add(new CodeDescription("306", "Server switch Proxy"));
            add(new CodeDescription("307", "Server Temporary Redirect"));
            add(new CodeDescription("308", "Server permanent Redirect"));
            add(new CodeDescription("400", "Server Bad Request"));
            add(new CodeDescription("401", "Server Unauthorized"));
            add(new CodeDescription("402", "Server Payment Required"));
            add(new CodeDescription("403", "Server Forbidden"));
            add(new CodeDescription("404", "Server Not Found"));
            add(new CodeDescription("405", "Server Request Method Not Allowed"));
            add(new CodeDescription("406", "Server request not acceptable"));
            add(new CodeDescription("407", "Server Proxy Authentication Required"));
            add(new CodeDescription("408", "Server Request Timeout"));
            add(new CodeDescription("409", "Server Conflict"));
            add(new CodeDescription("410", "Server Gone"));
            add(new CodeDescription("411", "The request did not specify the length of its content"));
            add(new CodeDescription("412", "Server Precondition Failed"));
            add(new CodeDescription("413", "Server Payload Too Large"));
            add(new CodeDescription("414", "Server  URI Too Long"));
            add(new CodeDescription("415", "Server Unsupported Media Type"));
            add(new CodeDescription("416", "Server Range Not Satisfiable"));
            add(new CodeDescription("417", "Server Expectation Failed"));
            add(new CodeDescription("418", "Server I'm a teapot"));
            add(new CodeDescription("421", "Server Misdirected Request"));
            add(new CodeDescription("422", "Server Uncrossable Entity"));
            add(new CodeDescription("423", "Server Locked"));
            add(new CodeDescription("424", "The request failed because it depended on another request"));
            add(new CodeDescription("426", "Server Upgrade Required"));
            add(new CodeDescription("428", "Server Precondition Required"));
            add(new CodeDescription("429", "Server too many request"));
            add(new CodeDescription("431", "Server Request Header Fields Too Large"));
            add(new CodeDescription("444", "Server No Response"));
            add(new CodeDescription("451", "Server Unavailable For Legal Reasons"));
            add(new CodeDescription("494", "Server Request header too large"));
            add(new CodeDescription("495", "SSL Certificate Error"));
            add(new CodeDescription("496", "SSL Certificate Required"));
            add(new CodeDescription("497", "HTTP Request Sent to HTTPS Port"));
            add(new CodeDescription("499", "Client Closed Request"));
            add(new CodeDescription("500", "Internal Server Error"));
            add(new CodeDescription("501", "The server does not recognize the request method"));
            add(new CodeDescription("502", "Bad Gateway"));
            add(new CodeDescription("503", "Server Service Unavailable"));
            add(new CodeDescription("504", "Server Gateway Timeout"));
            add(new CodeDescription("505", "Server HTTP Version Not Supported"));
            add(new CodeDescription("506", "Server Variant Also Negotiates"));
            add(new CodeDescription("508", "Server Loop Detected"));
            add(new CodeDescription("510", "Server Not Extended"));
            add(new CodeDescription("511", "Server Network Authentication Required"));
            add(new CodeDescription("521", "Web Server Is Down"));
            add(new CodeDescription("522", "Connection Timed Out"));
            add(new CodeDescription("523", "Origin Is Unreachable"));
            add(new CodeDescription("524", "A Timeout Occurred"));
            add(new CodeDescription("525", "SSL Handshake Failed"));
            add(new CodeDescription("526", "Server  Invalid SSL Certificate"));
            add(new CodeDescription("530", "Origin DNS Error"));
            add(new CodeDescription("598", "Server Network read timeout error"));
        }
    };

    public HttpCode(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }

    /**
     * getting the code response and search in the ArrayList
     * @param responseCode
     */
    public void CheckHttpCode(String responseCode) {
        for (int i = 0; i < httpCodesStatus.size(); i++) {
            if (httpCodesStatus.get(i).getCode().equals(responseCode)) {
                saveToStream(httpCodesStatus.get(i));
                return;
            }
        }
    }

    /**
     * getting code description and saving it to the current stream with the correct date
     * @param codeDescription
     */
    private void saveToStream(CodeDescription codeDescription) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd HH:mm");
        Date now = new Date();
        String dateName = formatter.format(now);

        printWriter.format("%s:\t %s %s\n", dateName,codeDescription.getCode(),codeDescription.getDescription());
        printWriter.flush();
    }
}
