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

    private static int control=0;

    /**
     * Função provisória para se inserir na base de dados as notícias e eventos. O objetivo é a base de dados ser alimentada pelo site oficial
     * da AE do ISCTE.
     */
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
        News feed2 = new News("Arraial do ISCTE", "Festas", "25/05/2018", R.drawable.event, true, true, "2");
        feed2.setEventDate("22/06/2018");
        feed2.setLocal("Pátio 1");
        feed2.setPrice(5);
        feed2.setText("A Comissão de Trabalhadores convida todos os trabalhadores do ISCTE-IUL (docentes, investigadores e técnicos) para o seu 1º Arraial a realizar no dia 22 de junho, a partir das 18h, no átrio exterior do Edifício Sedas Nunes.\n" +
                "\n" +
                "Juntemo-nos neste fim de tarde, acompanhados pelas sardinhas assadas, bifanas, música popular portuguesa e, claro, o obrigatório bailarico!\n" +
                "\n" +
                "O valor da inscrição é de 5 sardinhas.\n" +
                "Inscreve-te e entrega o valor da inscrição aos colegas Henrique Borges (SIIC), Rosário Candeias (SAS) ou Tiago Santos (GCM), até ao dia 20 de junho.\n" +
                "\n" +
                "Para qualquer esclarecimento, por favor contacta-nos através do e-mail ct@iscte-iul.pt");
        News feed3 = new News("Habemos NET!", "Núcleos", "24/05/2018", R.drawable.news, false, false, "3");
        feed3.setLocal("Auditório B2.04");
        feed3.setEventDate("06/08/2018");
        feed3.setText("O Núcleo de Estudantes de Tecnologia do ISCTE-IUL foi criado. A lista vencedora do primeiro mandato é a Lista C - Construimos Juntos. \n" +
        "A tomada de posse será no dia 4 de junho pelas 13h30 no auditório B2.04. \n" +
        "\n"+
        "Contamos contigo!");
        News feed4 = new News("Nova reitora", "ISCTE", "09/02/2018", R.drawable.news, false, false, "4");
        feed4.setText("O Conselho Geral elegeu a Professora Maria de Lurdes Rodrigues para ser a Reitora do ISCTE-IUL no período 2018-2022. É a primeira vez que uma mulher vai liderar a instituição.\n" +
                "\n" +
                "Maria de Lurdes Rodrigues é Professora Associada com Agregação no ISCTE-IUL, instituição onde se licenciou e doutorou em Sociologia e onde leciona desde 1986. É investigadora do CIES-IUL, Centro de Investigação e Estudos de Sociologia.\n" +
                "Entre 2005 e 2009 foi Ministra da Educação e entre 2010 e 2013 foi presidente do Conselho de Administração da FLAD-Fundação Luso-Americana para o Desenvolvimento.\n" +
                "\n" +
                "Ao Conselho de Curadores cabe a homologação da decisão do Conselho Geral, em reunião que decorrerá nos próximos dias.");
        News feed5 = new News("Santo António", "Festas", "06/06/2018", R.drawable.event, true, true, "5");
        feed5.setEventDate("12/06/2018");
        feed5.setPrice(2);
        feed5.setLocal("Lisboa");
        feed5.setText("Com um cheiro a verão no ar, as Festas de Lisboa enchem de animação os recantos mais típicos da cidade e trazem à rua milhares de pessoas. \n" +
                "Santo António, muito venerado em Lisboa e tratado como um verdadeiro padroeiro da cidade, dá o mote para as festas que atingem o seu ponto alto na noite de 12 de junho, com o desfile das marchas populares na Avenida da Liberdade. Na tarde do dia 13, a procissão em honra deste santo popular, que tem fama de casamenteiro, percorre as ruas que rodeiam a Sé e dá o cunho religioso às festas. \n" +
                "As noites são animadas pelos arraiais nos bairros típicos de Lisboa, do Castelo à Mouraria, Graça, Alfama, Ajuda e Bairro Alto, com muita música e dança ao ritmo das canções populares. Enfeitadas com grinaldas e globos coloridos, as ruas são invadidas pelo cheiro da sardinha assada e pelos aromas dos manjericos acompanhados de um cravo de papel e de uma quadra alusiva a Santo António.\n" +
                "\n" +
                "Junho é o principal mês destas festas, que se prolongam pelo verão e incluem eventos muito diversificados como espetáculos de fado, jazz e outros géneros musicais, fado nos elétricos que atravessam a cidade, festivais de cinema e teatro, provas desportivas e exposições.");

        databaseReference.child(feed1.getId()).setValue(feed1);
        databaseReference.child(feed2.getId()).setValue(feed2);
        databaseReference.child(feed3.getId()).setValue(feed3);
        databaseReference.child(feed4.getId()).setValue(feed4);
        databaseReference.child(feed5.getId()).setValue(feed5);

    }

    public static Card feedToCard(News feed){
        Card card = new Card(feed.getName(), feed.getCategory(), feed.getDate(), feed.getImage());
        card.setFeed(feed);
        return card;
    }

    public static Card ticketToCard(Ticket ticket, String valido){
        Card card = new Card(ticket.getName(), valido, ticket.getDate(), R.drawable.ticket);
        card.setTicket(ticket);
        return card;
    }

    public static void confirmTicket(final String contents, final Activity activity) {
        final String eventId = ReaderFragment.eventIdStrig;
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = firebaseDatabase.getReference("Tickets");
        control=1;

        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean find=false;
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    Ticket ticket = ds.getValue(Ticket.class);
                    if(ticket.getTicketId().equals(contents)){
                        if(ticket.getEventId().equals(eventId)){
                            if(ticket.getIsValidated()==false) {
                                Toast.makeText(activity, activity.getString(R.string.ticket_validated)+" - "+contents, Toast.LENGTH_LONG).show();
                                ticket.setIsValidated(true);
                                databaseReference.child(ticket.getTicketId()).child("isValidated").setValue(true);
                                control=0;
                                find = true;
                                return;
                            }else if(control==1){
                                Toast.makeText(activity, activity.getString(R.string.ticket_used)+" - "+contents, Toast.LENGTH_LONG).show();
                                find = true;
                            }
                        }
                    }
                }
                if(!find && control==1){
                    Toast.makeText(activity, activity.getString(R.string.ticket_not_found)+" - "+contents, Toast.LENGTH_LONG).show();
                }
                control=0;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
