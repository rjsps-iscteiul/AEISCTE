package com.example.ruijs.aeiscte;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.Random;


public class TicketFragment  extends Fragment {

    private String eventId;
    private String ticketId;
    private ImageView imageView;
    private Card card;

    public TicketFragment() {
        // Required empty public constructor
    }

    public void setEventId(String eventId){
        this.eventId = eventId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ticket, container, false);
        imageView = (ImageView) view.findViewById(R.id.event_qrcode_id);


        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try{
            randomNewTicketId();
            BitMatrix bitMatrix = multiFormatWriter.encode(ticketId, BarcodeFormat.QR_CODE, 200, 200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            imageView.setImageBitmap(bitmap);
        }catch (WriterException e){
            e.printStackTrace();
        }

        return view;
    }

    private void randomNewTicketId(){
        Random random = new Random();
        ticketId = String.valueOf(random.nextInt(100000)) + eventId;
    }

    public void associateToCard(Card card){
        this.card = card;
        eventId = card.getId();
    }

}




