package com.example.karan.am;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class RegestrationActivity extends AppCompatActivity {
MyHelper mydb;
   RadioGroup rg;
    RadioButton rb1,rb2;
    EditText nm13,add17,em26,phn16,wstp18,qu19,cst20;
    String typ;
    Button nxt;
    TextView tw;
/*public void checkbutton(View v)
{
    int rbid=rg.getCheckedRadioButtonId();
    rb= findViewById(rbid);
    typ=rb.getText().toString();

}*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regestration);
        mydb=new MyHelper(this);
        rg=(RadioGroup) findViewById(R.id.rg);
        rb1=(RadioButton) findViewById(R.id.radioButton);
        rb2=(RadioButton) findViewById(R.id.radioButton2);
        nm13=findViewById(R.id.editText13);
        add17=findViewById(R.id.editText17);
        em26=findViewById(R.id.editText26);
        phn16=findViewById(R.id.editText16);
        wstp18=findViewById(R.id.editText18);
        qu19=findViewById(R.id.editText19);
        cst20=findViewById(R.id.editText20);
        nxt=(Button)findViewById(R.id.button5);
        tw=(TextView)findViewById(R.id.TextView13);

        nm13.setError("enter name");
        add17.setError("enter email ID");
        phn16.setError("enter phone NO");
        wstp18.setError("enter user ID");
        qu19.setError("enter password");
        cst20.setError("enter confrim password");





        nm13.addTextChangedListener(abcd);
        add17.addTextChangedListener(abcd);
        em26.addTextChangedListener(abcd);
        phn16.addTextChangedListener(abcd);
        wstp18.addTextChangedListener(abcd);
        qu19.addTextChangedListener(abcd);
        cst20.addTextChangedListener(abcd);

        nm13.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(nm13.getText().length()<1 )
                {
                    nm13.setError("enter name");
                }
            }
        });

        add17.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(add17.getText().length()<1 )
                {
                    add17.setError("enter address");
                }
            }
        });



        phn16.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(phn16.getText().length()<1 )
                {
                    phn16.setError("enter phone no");
                }
            }
        });

        wstp18.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(wstp18.getText().length()<1 )
                {
                   wstp18.setError("enter userid");
                }
            }
        });

        qu19.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(qu19.getText().length()<1 )
                {
                    qu19.setError("enter password");
                }
            }
        });

        cst20.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(cst20.getText().length()<1 )
                {
                    cst20.setError("confirm password");
                }
            }
        });

     rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                typ="buyer";


            }
        });

       rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                typ="seller";
               //cst20.setEnabled(Boolean.parseBoolean("true"));
            }
        });


     nxt.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              String nmi=nm13.getText().toString().trim();
              String addi=add17.getText().toString().trim();
              String phni=phn16.getText().toString().trim();
              final String wstpi=wstp18.getText().toString().trim();
              final String qui=qu19.getText().toString().trim();
              String emi=em26.getText().toString().trim();
              final String csti=cst20.getText().toString().trim();
    if(typ == null)
    {
        Toast.makeText(RegestrationActivity.this,"please select UserType",Toast.LENGTH_SHORT).show();
    }
if(emi==null)
{
    emi=" ";
}
    else if(wstpi.length()<1 && qui.length()<1 && csti.length()<1 )
                {
                    wstp18.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            if(wstpi.length()<1 )
                            {
                                wstp18.setError("enter user  ID");
                            }
                        }
                    });

                    qu19.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            if(qui.length()<1 )
                            {
                                qu19.setError("enter Password ID");
                            }
                        }
                    });

                    cst20.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            if(csti.length()<1 )
                            {
                                cst20.setError("enter Confirm password ID");
                            }
                        }
                    });
                 //   submit.setEnabled(false);
                }
                else if(!csti.equals(qui))
                {
                    cst20.setError("wrong Confirm password ID");
                }
                else {

        if(typ!=null)
        {
    Boolean isInserted;
    isInserted=mydb.insertData(wstpi,nmi,addi,phni,emi,typ,qui);

    if(isInserted==true){
        Toast.makeText(RegestrationActivity.this,"data inserted successfully",Toast.LENGTH_SHORT).show();
        Intent nxti = new Intent(RegestrationActivity.this, MainActivity.class);
        startActivity(nxti);
    }

         else
             {
        Toast.makeText(RegestrationActivity.this,"data not inserted successfully",Toast.LENGTH_SHORT).show();
    }
     }}

          }
      });

    }
  private  TextWatcher abcd=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
      String nmi=nm13.getText().toString().trim();
            String addi=add17.getText().toString().trim();
            String phni=phn16.getText().toString().trim();
            String wstpi=wstp18.getText().toString().trim();
            String qui=qu19.getText().toString().trim();
            String emi=em26.getText().toString().trim();
            String csti=cst20.getText().toString().trim();
            nxt.setEnabled(!nmi.isEmpty() && !addi.isEmpty() && !phni.isEmpty() && !wstpi.isEmpty() && !qui.isEmpty() &&  !csti.isEmpty() );

        }

        @Override
        public void afterTextChanged(Editable s) {
            String nmi=nm13.getText().toString().trim();
            String addi=add17.getText().toString().trim();
            String phni=phn16.getText().toString().trim();
            String wstpi=wstp18.getText().toString().trim();
            String qui=qu19.getText().toString().trim();
            String emi=em26.getText().toString().trim();
            String csti=cst20.getText().toString().trim();


            nm13.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(nm13.getText().length()<1 )
                    {
                        nm13.setError("enter name");
                    }
                }
            });

            add17.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(add17.getText().length()<1 )
                    {
                        add17.setError("enter address");
                    }
                }
            });

            phn16.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(phn16.getText().length()<1 )
                    {
                        phn16.setError("enter phone no");
                    }
                }
            });

            wstp18.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(wstp18.getText().length()<1 )
                    {

                        wstp18.setError("enter userid");
                    }
                }
            });

            qu19.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(qu19.getText().length()<1 )
                    {
                        qu19.setError("enter password");
                    }
                }
            });

            cst20.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(cst20.getText().length()<1 )
                    {
                        cst20.setError("confirm password");
                    }

                }
            });


            if(nmi.length()<1 && addi.length()<1 && phni.length()<1 && wstpi.length()<1 && qui.length()<1 && csti.length()<1 )
{
    nm13.setOnFocusChangeListener(new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if(nm13.getText().length()<1 )
            {
                nm13.setError("enter name");
            }
        }
    });

    add17.setOnFocusChangeListener(new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if(add17.getText().length()<1 )
            {
                add17.setError("enter address");
            }
        }
    });


    phn16.setOnFocusChangeListener(new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if(phn16.getText().length()<1 )
            {
                phn16.setError("enter phone no");
            }
        }
    });

    wstp18.setOnFocusChangeListener(new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if(wstp18.getText().length()<1 )
            {
                wstp18.setError("enter userid");
            }
        }
    });

    qu19.setOnFocusChangeListener(new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if(qu19.getText().length()<1 )
            {
                qu19.setError("enter password");
            }
        }
    });

    cst20.setOnFocusChangeListener(new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if(cst20.getText().length()<1 )
            {
                cst20.setError("enter confirm password");
            }
        }
    });

    nxt.setEnabled(false);
}

        }
    };
}
