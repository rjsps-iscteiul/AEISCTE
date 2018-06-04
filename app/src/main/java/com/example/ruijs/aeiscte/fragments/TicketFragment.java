package com.example.ruijs.aeiscte.fragments;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ruijs.aeiscte.Card;
import com.example.ruijs.aeiscte.R;
import com.example.ruijs.aeiscte.objects.Ticket;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;


public class TicketFragment extends Fragment {

    private Ticket ticket;
    private ImageView imageView;

    public TicketFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ticket, container, false);
        imageView = (ImageView) view.findViewById(R.id.event_qrcode_id);

        ((TextView) view.findViewById(R.id.ticket_id_name)).setText(ticket.getName());

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try{
            BitMatrix bitMatrix = multiFormatWriter.encode(ticket.getTicketId(), BarcodeFormat.QR_CODE, 200, 200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            imageView.setImageBitmap(bitmap);
        }catch (WriterException e){
            e.printStackTrace();
        }

        return view;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void associateToTicket(Ticket ticket){
        this.ticket = ticket;
    }

}




