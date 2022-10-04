package kz.talipovsn.quadratic;

import android.annotation.SuppressLint;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Локальные переменные для доступа к компонентам окна
    private EditText editText_a, editText_b,editText_x;
    private TextView textView_y;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация переменных доступа к компонентам окна
        editText_a = findViewById(R.id.editText_a);
        editText_b = findViewById(R.id.editText_b);
        editText_x = findViewById(R.id.editText_x);
        textView_y = findViewById(R.id.textView_y);
        button = findViewById(R.id.button);

        // Собственный обработчик нажатий на клавиши
        View.OnKeyListener myKeyListener = new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // Проверка условия: если пусто в "a" или "b"
                if (editText_a.getText().toString().trim().equals("") ||
                        editText_b.getText().toString().trim().equals("")||editText_x.getText().toString().trim().equals("")
                ) {
                    button.setEnabled(false); // Выключаем доступность нажатия у кнопки
                } else {
                    button.setEnabled(true); // Включаем доступность нажатия у кнопки
                }
                return false;
            }
        };

        button.setEnabled(false); // Выключаем доступность нажатия у кнопки
        editText_a.setOnKeyListener(myKeyListener); // Добавляем к компоненту свой обработчик нажатий
        editText_b.setOnKeyListener(myKeyListener); // Добавляем к компоненту свой обработчик нажатий
        editText_x.setOnKeyListener(myKeyListener); // Добавляем к компоненту свой обработчик нажатий

        // Проверка на переворот экрана и восстановление нужных значений в компонентах
        if (savedInstanceState != null) {
            textView_y.setText(savedInstanceState.getString("y"));
        }
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Сохранение нужных нам значений компонент при перевороте экрана
        outState.putString("y", textView_y.getText().toString());
    }

    // МЕТОД ДЛЯ КНОПКИ РАСЧЕТА
    @SuppressLint("DefaultLocale")
    public void onCalc(View v) {
        double a, b, x, y;

        try {
            a = Double.parseDouble(editText_a.getText().toString().trim());
            b = Double.parseDouble(editText_b.getText().toString().trim());
            x = Double.parseDouble(editText_x.getText().toString().trim());
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "Неверные входные данные!",
                    Toast.LENGTH_LONG).show();
            return;
        }

        // Основной алгоритм
        if (x>=4) {
            y = 12 * (x/((a*a*a)-b));
        }
        else {
            y = 3 * Math.pow(x, 2) - a * b;
        }

        // Вывод полученного значения в компонент
        textView_y.setText(String.valueOf(y));

    }

}
