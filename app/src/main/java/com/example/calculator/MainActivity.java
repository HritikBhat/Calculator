package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

// ------------Stack Data Structure-------------------------------
class my_Stack
{
    ArrayList<String> Stack_List = new ArrayList();
    public int length() {
        return Stack_List.size();
    }

    public String pop() {
        String val =peek();
        Stack_List.remove(length() - 1);
        return val;
    }
    public String peek() {
        return Stack_List.get(Stack_List.size() - 1);
    }
    public void push(String tok) {
        Stack_List.add(tok);
    }
    public ArrayList<String> display() {
        return Stack_List;
    }
}
// ------------Queue Data Structure-------------------------------
class my_Queue
{
    ArrayList<String> Queue_List = new ArrayList();

    public void insert(String tok) {
        Queue_List.add(tok);
    }
    public ArrayList<String> display() {
        return Queue_List;
    }
}

class post_Converter
{
    public String start(List<String> arr) {
        postfix_Evaluation pe = new postfix_Evaluation();
        my_Stack st = new my_Stack();
        my_Queue q = new my_Queue();
        for (String token : arr) {
            if(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")||token.equals("("))
            {
                try {
                    if (!st.peek().equals("(")) {
                        if ((st.peek().equals("+") || st.peek().equals("-") || st.peek().equals("*") || st.peek().equals("/")) && (token.equals("*") || token.equals("/"))) {
                            if (st.peek().equals("/") && token.equals("*"))
                            {q.insert(st.pop());}
                            else if ((st.peek().equals("*") || st.peek().equals("/")) && st.display().contains("("))
                                q.insert(st.pop());
                        }
                        else if ((st.peek().equals("+") || st.peek().equals("-") || st.peek().equals("*") || st.peek().equals("/")) && (token.equals("+") || token.equals("-"))) {
                            for (int i = 0; i < st.length(); i++) {
                                if (st.peek().equals("("))
                                    break;
                                q.insert(st.pop());
                            }
                        }
                    }}catch (Exception e){}
                st.push(token);
            }
            else if(token.equals(")"))
            {
                for (int i =0;i<st.length();i++)
                {
                    if (st.peek().equals("("))
                        break;
                    q.insert(st.pop());
                }
            }
            else
                q.insert(token);
            //System.out.println("token: "+token);
            //System.out.println("stack: "+st.display());
            //System.out.println("queue: "+q.display()+"\n");
        }
        //For loop ends
        while (st.length()>0)
        {
            if (st.peek().equals("("))
            {st.pop();continue;}
            q.insert(st.pop());
        }
        //System.out.println(q.display());
        return pe.start(q.display());
    }
}
class postfix_Evaluation
{
    public String start(List<String> arr)
    {
        my_Stack st = new my_Stack();
        for (String token:arr) {
            if (token.isEmpty())
                continue;
            if(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")){
                float result=0;
                float a = (Float.parseFloat(st.pop()));
                float b = (Float.parseFloat(st.pop()));
                switch(token)
                {
                    case "+":
                        result = b + a;
                        break;
                    case "-":
                        result = b - a;
                        break;
                    case "*":
                        result = b * a;
                        break;
                    case "/":
                        result = b / a;
                        break;
                }
                st.push(Float.toString(result));

            }
            else
            {
                st.push(token);
            }
            //System.out.println(st.display());
        }
        return st.peek();
    }
}

public class MainActivity extends AppCompatActivity {
    private Button zero, one, two, three, four, five, six, seven, eight, nine;
    private Button add, sub, mul, div, eq, op_brac, cl_brac, clear, dot,back;
    private TextView Display;
    String number="";
    ArrayList<String> arr =new ArrayList();

    protected  void return_Number(String sign){
        arr.add(number);
        if (!sign.equals(" "))
            arr.add(sign);
        System.out.println(arr);
        number="";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI_Views();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s =Display.getText().toString();
                Display.setText(s.substring(0,s.length()-1));
                if (!number.equals(""))
                {number = number.substring(0, number.length() - 1);
                    System.out.println(number);}
                else
                {
                    if ((arr.get(arr.size()-1)).length()==1 || arr.get(arr.size()-1).contains("/") || arr.get(arr.size()-1).contains("*") || arr.get(arr.size()-1).contains("+") || arr.get(arr.size()-1).contains("-")||arr.get(arr.size()-1).contains("(")||arr.get(arr.size()-1).contains(")")||arr.get(arr.size()-1).equals(" ")) {
                        arr.remove(arr.size() - 1);
                        System.out.println(arr);
                    }
                    else
                    {
                        String val =arr.get(arr.size()-1);
                        arr.remove(arr.size()-1);
                        val = val.substring(0, val.length() - 1);
                        arr.add(val);
                        System.out.println(arr);
                    }

                }
            }
        });
        //Set OnListener For Clearing the Display
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Display.setText("");
                number="";
                arr.clear();
            }
        });

        //Set OnListener For Numbers
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Display.setText(Display.getText().toString() + "0");
                number=number+"0";

            }
        });
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Display.setText(Display.getText().toString() + "1");
                number=number+"1";
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Display.setText(Display.getText().toString() + "2");
                number=number+"2";
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Display.setText(Display.getText().toString() + "3");
                number=number+"3";
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Display.setText(Display.getText().toString() + "4");
                number=number+"4";
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Display.setText(Display.getText().toString() + "5");
                number=number+"5";
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Display.setText(Display.getText().toString() + "6");
                number=number+"6";
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Display.setText(Display.getText().toString() + "7");
                number=number+"7";
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Display.setText(Display.getText().toString() + "8");
                number=number+"8";
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Display.setText(Display.getText().toString() + "9");
                number=number+"9";
            }
        });

        //Set OnListener for Operators
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(arr.size()>1){
                if (arr.get(arr.size()-1).contains("-")||arr.get(arr.size()-1).contains("*")||arr.get(arr.size()-1).contains("/"))
                {   Display.setText(Display.getText().toString().substring(0,Display.getText().toString().length()-1) + "+");
                    arr.set(arr.size()-1,"+");
                    System.out.println(arr);
                }}
                else {
                    Display.setText(Display.getText().toString() + "+");
                    return_Number("+");
                }

            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(arr.size()>1)
                {
                if (arr.get(arr.size()-1).contains("+")||arr.get(arr.size()-1).contains("*")||arr.get(arr.size()-1).contains("/"))
                {   Display.setText(Display.getText().toString().substring(0,Display.getText().toString().length()-1) + "-");
                    arr.set(arr.size()-1,"-");
                    System.out.println(arr);
                }}
                else if (arr.get(arr.size()-1).contains("("))
                {Display.setText(Display.getText().toString() + "-");
                    number = number + "-";}
                else{
                    Display.setText(Display.getText().toString() + "-");
                    return_Number("-");}
            }
        });
        mul.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(arr.size()>1)
                {
                if (arr.get(arr.size()-1).contains("-")||arr.get(arr.size()-1).contains("+")||arr.get(arr.size()-1).contains("/"))
                {   Display.setText(Display.getText().toString().substring(0,Display.getText().toString().length()-1) + "*");
                    arr.set(arr.size()-1,"*");
                    System.out.println(arr);
                }}
                else {
                    Display.setText(Display.getText().toString() + "*");
                    return_Number("*");
                }

            }
        });
        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(arr.size()>1)
                {if (arr.get(arr.size()-1).contains("-")||arr.get(arr.size()-1).contains("+")||arr.get(arr.size()-1).contains("*"))
                {   Display.setText(Display.getText().toString().substring(0,Display.getText().toString().length()-1) + "/");
                    arr.set(arr.size()-1,"/");
                    System.out.println(arr);
                }}
                else {
                    Display.setText(Display.getText().toString() + "/");
                    return_Number("/");
                }
            }
        });

        //Set OnListener For the brackets
        op_brac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Display.setText(Display.getText().toString() + "(");
                arr.add("(");
                //System.out.println(arr);
            }
        });
        cl_brac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Display.setText(Display.getText().toString() + ")");
                return_Number(")");
                //System.out.println(arr);
            }
        });
        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Display.setText(Display.getText().toString() + ".");
                number=number+".";
            }
        });
        eq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                return_Number(" ");
                post_Converter p = new post_Converter();
                for (int i=0;i<arr.size();i++)
                {
                    if(arr.get(i).equals(" ")||arr.get(i).equals(""))
                        arr.remove(i);
                }
                System.out.println("Done:"+arr);

                for(int i=0;i<arr.size()-3;i++)
                {
                    if (arr.get(i).equals("(") && arr.get(i+1).equals("-") && arr.get(i+3).equals(")"))
                    {
                        String val = arr.get(i+1) + arr.get(i+2);

                        arr.remove(i+1);

                        arr.remove(i+1);

                        arr.add(i+1,val);

                    }}
                System.out.println("Done:"+arr);
                number=p.start(arr);
                arr.clear();
                Display.setText(number);

            }
        });


    }
    private void setupUI_Views()
    {
        //Numbers
        zero = (Button)findViewById(R.id.b0);
        one = (Button)findViewById(R.id.b1);
        two = (Button)findViewById(R.id.b2);
        three = (Button)findViewById(R.id.b3);
        four = (Button)findViewById(R.id.b4);
        five = (Button)findViewById(R.id.b5);
        six = (Button)findViewById(R.id.b6);
        seven = (Button)findViewById(R.id.b7);
        eight = (Button)findViewById(R.id.b8);
        nine = (Button)findViewById(R.id.b9);

        //Signs
        add = (Button)findViewById(R.id.b_add);
        sub = (Button)findViewById(R.id.b_sub);
        mul = (Button)findViewById(R.id.b_mul);
        div = (Button)findViewById(R.id.b_div);
        eq = (Button)findViewById(R.id.b_equal);
        dot = (Button)findViewById(R.id.b_dot);
        //Bracket
        op_brac = (Button)findViewById(R.id.b_open_brac);
        cl_brac = (Button)findViewById(R.id.b_closed_brac);
        clear = (Button)findViewById(R.id.b_clear);

        back = (Button)findViewById(R.id.b_back);

        //Display
        Display = (TextView) findViewById(R.id.Display);
    }
}
