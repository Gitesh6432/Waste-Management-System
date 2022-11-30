package com.example.karan.am;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.KeyEvent.KEYCODE_BACK;
import static android.view.KeyEvent.KEYCODE_BOOKMARK;

public class CreateAcD extends AppCompatActivity {
    MyHelper db=new MyHelper(this);
    Button submit;
    EditText username,cost,quantity,wastetype;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ac_d);
        final Intent intent=getIntent();
        final String UID=intent.getStringExtra("username");
        TextView user=(TextView)findViewById(R.id.title_seller);
        user.setText("Hello "+UID);
      ImageButton logout=(ImageButton) findViewById(R.id.logout_seller);
      submit=(Button)findViewById(R.id.button3);
        cost=(EditText)findViewById(R.id.editText6);
        quantity=(EditText)findViewById(R.id.editText7);
        wastetype=(EditText)findViewById(R.id.editText8);


        cost.setError("enter cost");
        quantity.setError("enter quantity");
        wastetype.setError("enter waste type");


        wastetype.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(wastetype.getText().length()<1 )
                {
                    wastetype.setError("enter waste type");
                }
            }
        });

        cost.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(cost.getText().length()<1 )
                {
                    cost.setError("enter cost");
                }
            }
        });

        quantity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(quantity.getText().length()<1 )
                {
                    quantity.setError("enter quantity");
                }
            }
        });

        cost.addTextChangedListener(abcde);
        quantity.addTextChangedListener(abcde);
        wastetype.addTextChangedListener(abcde);

     submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String costi=cost.getText().toString().trim();
                String quantityi=quantity.getText().toString().trim();
                String ui=wastetype.getText().toString().trim();
                if(ui.length()<1 && costi.length()<1 && quantityi.length()<1 )
                {

                    cost.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            if(cost.getText().length()<1 )
                            {
                                cost.setError("enter cost");
                            }
                        }
                    });

                    quantity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            if(quantity.getText().length()<1 )
                            {
                                quantity.setError("enter quantity");
                            }
                        }
                    });
                    wastetype.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            if(wastetype.getText().length()<1 )
                            {
                                wastetype.setError("enter quantiyty");
                            }
                        }
                    });

                }

                else {
                    Boolean isSold=db.Sell(UID,ui,quantityi,costi);
                    if(isSold) {

                        Toast.makeText(getApplicationContext(), "Product Updated", Toast.LENGTH_SHORT).show();

                    }
                    else
                        Toast.makeText(getApplicationContext(),"Failed to Update",Toast.LENGTH_SHORT).show();
                    }

            }
        });


     logout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent1=new Intent(CreateAcD.this,MainActivity.class);
            Toast.makeText(getApplicationContext(),"Log Out Successfully",Toast.LENGTH_SHORT).show();
            startActivity(intent1);

        }
        });


    }
    public TextWatcher abcde=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String costi=cost.getText().toString().trim();
            String quantityi=quantity.getText().toString().trim();
            String ui=quantity.getText().toString().trim();


            cost.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(cost.getText().length()<1 )
                    {
                        cost.setError("enter cost");
                    }
                }
            });

            quantity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(quantity.getText().length()<1 )
                    {
                        quantity.setError("enter quantity");
                    }
                }
            });
            wastetype.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(wastetype.getText().length()<1 )
                    {
                        wastetype.setError("enter waste type");
                    }
                }
            });

           submit.setEnabled( !costi.isEmpty() && !quantityi.isEmpty() && !ui.isEmpty() );

        }

        @Override
        public void afterTextChanged(Editable s) {
            //String usernamei=username.getText().toString().trim();
            String costi=cost.getText().toString().trim();
            String quantityi=quantity.getText().toString().trim();
            String ui=wastetype.getText().toString().trim();

            if(ui.length()<1 && costi.length()<1 && quantityi.length()<1  && ui.length()<1)
            {
                username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if(username.getText().length()<1 )
                        {
                            username.setError("enter user  ID");
                        }
                    }
                });

                cost.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if(cost.getText().length()<1 )
                        {
                            cost.setError("enter cost");
                        }
                    }
                });

                quantity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if(quantity.getText().length()<1 )
                        {
                            quantity.setError("enter quantity");
                        }
                    }
                });
                wastetype.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if(wastetype.getText().length()<1 )
                        {
                            wastetype.setError("enter waste type");
                        }
                    }
                });
                submit.setEnabled(false);
            }

            }

        };

}
