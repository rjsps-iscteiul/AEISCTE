package com.example.ruijs.aeiscte;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.ruijs.aeiscte.fragments.ReaderFragment;
import com.example.ruijs.aeiscte.objects.News;
import com.example.ruijs.aeiscte.objects.Ticket;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FeedFactory {

    public static void createFeed(){
        //DataBase Feed
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Feed");

        News feed1 = new News("Festa do Caloiro", "Festas", "03/10/2017", R.drawable.event, true, true, "1");
        feed1.setEventDate("29/10/2017");
        feed1.setPrice(12);
        feed1.setLocal("ISCTE-IUL");
        feed1.setText("Está oficialmente lançado o cartaz final da 21ª edição da FESTA DO CALOIRO!\n" +
                "3 palcos, 12 artistas e 5 horas de muita música!\n" +
                "É já dia 28 deste mês que o ISCTE vai-te dar a melhor Festa Universitária de Lisboa.\n" +
                "\n" +
                "Prepara-te, o countdown já começou e os bilhetes estarão à venda muito em breve.");
        News feed2 = new News("Arraial do ISCTE", "Festas", "25/5/2018", R.drawable.event, true, true, "2");
        feed2.setEventDate("8/6/2018");
        feed2.setLocal("Pátio 1");
        feed2.setPrice(5);
        feed2.setText("A Comissão de Trabalhadores convida todos os trabalhadores do ISCTE-IUL (docentes, investigadores e técnicos) para o seu 1º Arraial a realizar no dia 8 de junho, a partir das 18h, no átrio exterior do Edifício Sedas Nunes.\n" +
                "\n" +
                "Juntemo-nos neste fim de tarde, acompanhados pelas sardinhas assadas, bifanas, música popular portuguesa e, claro, o obrigatório bailarico!\n" +
                "\n" +
                "O valor da inscrição é de 5 sardinhas.\n" +
                "Inscreve-te e entrega o valor da inscrição aos colegas Henrique Borges (SIIC), Rosário Candeias (SAS) ou Tiago Santos (GCM), até ao dia 6 de junho.\n" +
                "\n" +
                "Para qualquer esclarecimento, por favor contacta-nos através do e-mail ct@iscte-iul.pt");
        News feed3 = new News("Habemos NET!", "Núcleos", "24/5/2018", R.drawable.event, false, false, "3");
        feed3.setLocal("Auditório B2.04");
        feed3.setEventDate("6/8/2018");
        feed3.setText("O Núcleo de Estudantes de Tecnologia do ISCTE-IUL foi criado. A lista vencedora do primeiro mandato é a Lista C - Construimos Juntos. \n" +
        "A tomada de posse será no dia 4 de junho pelas 13h30 no auditório B2.04. \n" +
        "\n"+
        "Contamos contigo!");
        News feed4 = new News("Sèrgio é pai", "ETI", "28/5/2018", R.drawable.event, false, false, "4");
        feed4.setText("O professor de Eletromagnetismo e de Propagação e Radiação de Ondas Eletromagnéticas, Sérgio Matos, foi pai de uma menina. \n" +
        "O nascimento da filha obrigou ao adiamente do teste de PROE para a semana seguinte, dia 4 de junho. \n" +
        "Desejamos as maiores felicidades.\n");

        Log.d("sdadas",databaseReference.toString());
        databaseReference.child(feed1.getId()).setValue(feed1);
        databaseReference.child(feed2.getId()).setValue(feed2);
        databaseReference.child(feed3.getId()).setValue(feed3);
        databaseReference.child(feed4.getId()).setValue(feed4);

    }

    public static Card feedToCard(News feed){
        Card card = new Card(feed.getName(), feed.getCategory(), feed.getDate(), feed.getImage());
        card.setFeed(feed);
        return card;
    }

    public static Card ticketToCard(Ticket ticket){
        Card card = new Card(ticket.getName(), "Ticket", ticket.getDate(), R.drawable.ticket);
        card.setTicket(ticket);
        return card;
    }

    public static void confirmTicket(final String contents, final Activity activity) {
        final String eventId = ReaderFragment.eventId;

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Tickets");

        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    Ticket ticket = ds.getValue(Ticket.class);
                    if(ticket.getTicketId().equals(contents)){
                        if(ticket.getEventId().equals(eventId)){
                            Toast.makeText(activity, "BILHETE VÁLIDO", Toast.LENGTH_LONG).show();
                        }
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
