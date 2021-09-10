package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import org.json.JSONArray;
import org.json.JSONObject;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.PrimitiveElement;

public class Controller {
    private TextField[][] textFields;

    @FXML private TextField res_1;@FXML private Label label_kramer;@FXML private Button solve;@FXML private TextField tf;
    @FXML private GridPane gridPane;@FXML private TextField b;@FXML private TextField a;@FXML TextField integral;
    @FXML private TextField solve_integral;@FXML private Button solve2;@FXML private Label label1;@FXML private Label label2;
    @FXML private Label label;@FXML private Label label_3;@FXML private TextField nolinear_ur;@FXML private TextField result_nolinear_ur;
    @FXML private TextField tochnost;@FXML private TextField x0;@FXML private Label label4;@FXML private Label label5;
    @FXML private Label label10;@FXML private Label label6;@FXML private Label label7;@FXML private Button solve3;
    @FXML private Label x3;@FXML private Label x2;@FXML private Label x1;@FXML private Label x_0;@FXML private TextField cub_d;
    @FXML private TextField cub_a;@FXML private TextField cub_b;@FXML private TextField cub_c;@FXML private TextField cub_0;
    @FXML private TextField result_cub;@FXML private Button solve_cub;@FXML private Label head;@FXML private Label label9;
    @FXML private Label er1;@FXML private Label er2;@FXML private Label er3;@FXML private Label er4;private static boolean err1;
    private static boolean err2;private static boolean err3;private static boolean err4;private static boolean s_err1;
    @FXML private Label server_error1;@FXML private Button clear1;@FXML private Button clear2;@FXML private Button clear3;
    @FXML private Button clear4;private int razmer;static String zagolovok;static final double width = 255.0D;static final double iks = 214.0D;

    @FXML
    private void open_slay_solver(ActionEvent event) {
        this.head.setText("Решение СЛАУ методом Крамера");
        zagolovok = this.head.getText();
        this.setVisible_menu_1(true);
        this.setVisible_menu_2(false);
        this.setVisible_menu_3(false);
        this.setVisible_menu_4(false);
    }

    @FXML
    private void open_integral_solver(ActionEvent event) {
        this.head.setText("Расчет определенного интеграла методом средних прямоугольников");
        zagolovok = this.head.getText();
        this.setVisible_menu_1(false);
        this.setVisible_menu_2(true);
        this.setVisible_menu_3(false);
        this.setVisible_menu_4(false);
    }

    @FXML
    private void open_nolinear_ur_solver(ActionEvent event) {
        this.head.setText("Решение нелинейного уравнения методом Ньютона");
        zagolovok = this.head.getText();
        this.setVisible_menu_1(false);
        this.setVisible_menu_2(false);
        this.setVisible_menu_3(true);
        this.setVisible_menu_4(false);
    }

    @FXML
    private void open_cub_solver(ActionEvent event) {
        this.head.setText("Решение кубического уравнения с помощью формул Виета");
        zagolovok = this.head.getText();
        this.setVisible_menu_1(false);
        this.setVisible_menu_2(false);
        this.setVisible_menu_3(false);
        this.setVisible_menu_4(true);
    }

    @FXML
    private void create_SLAY(ActionEvent event) {
        try {
            this.razmer = Integer.parseInt(this.tf.getText());
            if (this.razmer <= 0) {
                this.er1.setVisible(true);
                err1 = true;
                return;
            }
        } catch (Throwable var10) {
            this.er1.setVisible(true);
            err1 = true;
            return;
        }

        if (this.razmer <= 7) {
            this.er1.setVisible(false);
            err1 = false;
            int i;
            int j;
            if (this.textFields != null) {
                TextField[][] var2 = this.textFields;
                i = var2.length;

                for(j = 0; j < i; ++j) {
                    TextField[] t = var2[j];
                    TextField[] var6 = t;
                    int var7 = t.length;

                    for(int var8 = 0; var8 < var7; ++var8) {
                        TextField t1 = var6[var8];
                        t1.setVisible(false);
                    }
                }
            }

            this.res_1.setText("");
            this.res_1.setAlignment(Pos.CENTER);
            this.res_1.setDisable(true);
            int n = 0;
            this.textFields = new TextField[this.razmer][this.razmer + 1];

            for(i = 0; i < this.razmer; ++i) {
                for(j = 0; j < this.razmer + 1; ++j) {
                    if (n < this.razmer + 1) {
                        this.gridPane.getColumnConstraints().add(new ColumnConstraints(50.0D));
                        if (n < this.razmer) {
                            this.gridPane.getRowConstraints().add(new RowConstraints(40.0D));
                        }

                        ++n;
                    }

                    this.textFields[i][j] = new TextField();
                    if (j == this.razmer) {
                        this.textFields[i][j].setPromptText("=");
                    } else {
                        this.textFields[i][j].setPromptText("x" + (j + 1));
                    }

                    this.gridPane.add(this.textFields[i][j], j, i);
                }
            }

            this.res_1.setPrefWidth(255.0D);
            this.res_1.setLayoutX(214.0D);
            this.gridPane.setLayoutX((double)(310 - 22 * this.razmer));
            if (this.razmer > 3) {
                if (this.razmer == 7) {
                    this.res_1.setPrefWidth(this.res_1.getPrefWidth() + (double)((this.razmer - 1) * (this.razmer - 1) * (this.razmer - 1)) + (double)((this.razmer - 1) * (this.razmer - 1) / 2));
                    this.res_1.setLayoutX(this.res_1.getLayoutX() - (double)((this.razmer - 1) * (this.razmer - 1) * (this.razmer - 1) / 2));
                } else {
                    this.res_1.setPrefWidth(this.res_1.getPrefWidth() + (double)(this.razmer * this.razmer * this.razmer) + (double)(this.razmer * this.razmer / 2));
                    this.res_1.setLayoutX(this.res_1.getLayoutX() - (double)(this.razmer * this.razmer * this.razmer / 2));
                }
            }

        }
    }

    private void setVisible_menu_4(Boolean bool) {
        if (err4) {
            this.er4.setVisible(bool);
        }

        this.x1.setVisible(bool);
        this.x2.setVisible(bool);
        this.x3.setVisible(bool);
        this.label9.setVisible(bool);
        this.label10.setVisible(bool);
        this.x_0.setVisible(bool);
        this.cub_0.setVisible(bool);
        this.cub_a.setVisible(bool);
        this.cub_b.setVisible(bool);
        this.cub_d.setVisible(bool);
        this.cub_c.setVisible(bool);
        this.result_cub.setVisible(bool);
        this.solve_cub.setVisible(bool);
        this.clear4.setVisible(bool);
    }

    private void setVisible_menu_3(Boolean bool) {
        if (err3) {
            this.er3.setVisible(bool);
        }

        this.label4.setVisible(bool);
        this.label5.setVisible(bool);
        this.label6.setVisible(bool);
        this.label7.setVisible(bool);
        this.nolinear_ur.setVisible(bool);
        this.result_nolinear_ur.setVisible(bool);
        this.tochnost.setVisible(bool);
        this.x0.setVisible(bool);
        this.solve3.setVisible(bool);
        this.clear3.setVisible(bool);
    }

    private void setVisible_menu_2(Boolean bool) {
        if (err2) {
            this.er2.setVisible(bool);
        }

        this.label.setVisible(bool);
        this.label1.setVisible(bool);
        this.label2.setVisible(bool);
        this.solve2.setVisible(bool);
        this.solve_integral.setVisible(bool);
        this.integral.setVisible(bool);
        this.a.setVisible(bool);
        this.b.setVisible(bool);
        this.clear2.setVisible(bool);
    }

    private void setVisible_menu_1(Boolean bool) {
        if (err1) {
            this.er1.setVisible(bool);
        }

        if (s_err1) {
            this.server_error1.setVisible(bool);
        }

        this.solve.setVisible(bool);
        this.res_1.setVisible(bool);
        this.clear1.setVisible(bool);
        this.label_3.setVisible(bool);
        this.label_kramer.setVisible(bool);
        this.tf.setVisible(bool);
        if (this.textFields != null) {
            TextField[][] var2 = this.textFields;
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                TextField[] t = var2[var4];
                TextField[] var6 = t;
                int var7 = t.length;

                for(int var8 = 0; var8 < var7; ++var8) {
                    TextField t1 = var6[var8];
                    t1.setVisible(bool);
                }
            }
        }

    }

    @FXML
    private void solve(ActionEvent event) throws Exception {
        if (err1) {
            this.er1.setVisible(false);
            err1 = false;
        }

        if (s_err1) {
            this.server_error1.setVisible(false);
            s_err1 = false;
        }

        this.res_1.setAlignment(Pos.CENTER);
        this.res_1.setDisable(true);
        Client_.line = new JSONObject();
        Client_.line.put("id", "1");
        Client_.line.put("razmer", this.razmer);
        double[][] masA = new double[this.razmer][this.razmer];
        double[] masB = new double[this.razmer];

        for(int i = 0; i < this.razmer; ++i) {
            for(int j = 0; j < this.razmer; ++j) {
                masA[i][j] = (new Expression(this.textFields[i][j].getText(), new PrimitiveElement[0])).calculate();
            }

            masB[i] = (new Expression(this.textFields[i][this.razmer].getText(), new PrimitiveElement[0])).calculate();
        }

        Client_.line.put("masA", masA);
        Client_.line.put("masB", masB);
        (new Client_()).client_go();
        String line = "";
        if (Client_.line.get("result").equals("Server_error")) {
            this.res_1.setText("Error");
            s_err1 = true;
            this.server_error1.setVisible(true);
        } else if (Client_.line.get("result").equals("Error")) {
            this.res_1.setText("Error");
            err1 = true;
            this.er1.setVisible(true);
        } else {
            JSONArray jsonArray = (JSONArray)Client_.line.get("result");

            for(int i = 0; i < this.razmer; ++i) {
                String result = String.format("%.2f", Double.parseDouble(jsonArray.get(i).toString()));
                int point_index = result.indexOf(",") + 1;
                int number_of_zeros = 0;

                for(int j = point_index; j < result.length(); ++j) {
                    if (Character.getNumericValue(result.charAt(j)) == 0) {
                        ++number_of_zeros;
                    } else {
                        number_of_zeros = 0;
                    }
                }

                if (i == this.razmer - 1) {
                    line = line + String.format("x" + (i + 1) + " = %." + (2 - number_of_zeros) + "f", Double.parseDouble(jsonArray.get(i).toString()));
                } else {
                    line = line + String.format("x" + (i + 1) + " = %." + (2 - number_of_zeros) + "f; ", Double.parseDouble(jsonArray.get(i).toString()));
                }

                if (i == this.razmer - 1) {
                    int number_of_symbols = 0;
                    if (this.razmer == 1) {
                        number_of_symbols = 34;
                    } else if (this.razmer == 2) {
                        number_of_symbols = 37;
                    } else if (this.razmer == 3) {
                        number_of_symbols = 39;
                    } else if (this.razmer == 4) {
                        number_of_symbols = 54;
                    } else if (this.razmer == 5) {
                        number_of_symbols = 62;
                    } else if (this.razmer == 6) {
                        number_of_symbols = 79;
                    } else if (this.razmer == 7) {
                        number_of_symbols = 82;
                    }

                    if (line.length() > number_of_symbols) {
                        this.res_1.setAlignment(Pos.CENTER_LEFT);
                        this.res_1.setDisable(false);
                    }
                }
            }

            this.res_1.setText(line);
        }

    }

    @FXML
    private void solve2(ActionEvent event) throws Exception {
        if (err2) {
            this.er2.setVisible(false);
            err2 = false;
        }

        this.solve_integral.setAlignment(Pos.CENTER);
        this.solve_integral.setDisable(true);
        Client_.line = new JSONObject();
        Client_.line.put("id", "2");
        Client_.line.put("integral", this.integral.getText());
        Client_.line.put("a", this.a.getText());
        Client_.line.put("b", this.b.getText());
        (new Client_()).client_go();
        if (Client_.line.get("result").equals("Server_error")) {
            this.solve_integral.setText("Error");
            this.er2.setText("Отсутствует соединение с сервером");
            this.er2.setLayoutX(240.0D);
            err2 = true;
            this.er2.setVisible(true);
        } else if (Client_.line.get("result").equals("Error")) {
            this.solve_integral.setText("Error");
            this.er2.setText("Некорректный ввод данных");
            this.er2.setLayoutX(264.0D);
            err2 = true;
            this.er2.setVisible(true);
        } else {
            String result = String.format("%.5f", Double.parseDouble(Client_.line.get("result").toString()));
            int point_index = result.indexOf(",") + 1;
            int number_of_zeros = 0;

            for(int j = point_index; j < result.length(); ++j) {
                if (Character.getNumericValue(result.charAt(j)) == 0) {
                    ++number_of_zeros;
                } else {
                    number_of_zeros = 0;
                }
            }

            if (String.format("%." + (5 - number_of_zeros) + "f", Double.parseDouble(Client_.line.get("result").toString())).length() > 21) {
                this.solve_integral.setAlignment(Pos.CENTER_LEFT);
                this.solve_integral.setDisable(false);
            }

            if (String.format("%." + (5 - number_of_zeros) + "f", Double.parseDouble(Client_.line.get("result").toString())).equals("0")) {
                this.solve_integral.setText(Client_.line.get("result").toString());
            } else {
                this.solve_integral.setText(String.format("%." + (5 - number_of_zeros) + "f", Double.parseDouble(Client_.line.get("result").toString())));
            }
        }

    }

    @FXML
    private void solve3(ActionEvent event) throws Exception {
        if (err3) {
            this.er3.setVisible(false);
            err3 = false;
        }

        this.result_nolinear_ur.setAlignment(Pos.CENTER);
        this.result_nolinear_ur.setDisable(true);
        Client_.line = new JSONObject();
        Client_.line.put("id", "3");
        Client_.line.put("nolinear_ur", this.nolinear_ur.getText());
        Client_.line.put("x0", this.x0.getText());
        Client_.line.put("tochnost", this.tochnost.getText());
        (new Client_()).client_go();
        if (Client_.line.get("result").equals("Server_error")) {
            this.result_nolinear_ur.setText("Error");
            this.er3.setText("Отсутствует соединение с сервером");
            this.er3.setLayoutX(240.0D);
            err3 = true;
            this.er3.setVisible(true);
        } else if (Client_.line.get("result").equals("Neshod")) {
            this.result_nolinear_ur.setText("");
            this.er3.setText("Метод не сошелся");
            this.er3.setLayoutX(289.0D);
            err3 = true;
            this.er3.setVisible(true);
        } else if (Client_.line.get("result").equals("Error")) {
            this.result_nolinear_ur.setText("Error");
            this.er3.setText("Некорректный ввод данных");
            this.er3.setLayoutX(262.0D);
            err3 = true;
            this.er3.setVisible(true);
        } else {
            String result = String.format("%.6f", Double.parseDouble(Client_.line.get("result").toString()));
            int point_index = result.indexOf(",") + 1;
            int number_of_zeros = 0;

            for(int j = point_index; j < result.length(); ++j) {
                if (Character.getNumericValue(result.charAt(j)) == 0) {
                    ++number_of_zeros;
                } else {
                    number_of_zeros = 0;
                }
            }

            if (String.format("%." + (6 - number_of_zeros) + "f", Double.parseDouble(Client_.line.get("result").toString())).length() > 24) {
                this.result_nolinear_ur.setAlignment(Pos.CENTER_LEFT);
                this.result_nolinear_ur.setDisable(false);
            }

            if (String.format("%." + (6 - number_of_zeros) + "f", Double.parseDouble(Client_.line.get("result").toString())).equals("0")) {
                this.result_nolinear_ur.setText("x = " + Client_.line.get("result").toString());
            } else {
                this.result_nolinear_ur.setText(String.format("x = %." + (6 - number_of_zeros) + "f", Double.parseDouble(Client_.line.get("result").toString())));
            }
        }

    }

    @FXML
    private void solve4(ActionEvent event) throws Exception {
        if (err4) {
            this.er4.setVisible(false);
            err4 = false;
        }

        this.result_cub.setAlignment(Pos.CENTER);
        this.result_cub.setDisable(true);
        Client_.line = new JSONObject();
        Client_.line.put("id", "4");
        Client_.line.put("cub_d", this.cub_d.getText());
        Client_.line.put("cub_a", this.cub_a.getText());
        Client_.line.put("cub_b", this.cub_b.getText());
        Client_.line.put("cub_c", this.cub_c.getText());
        (new Client_()).client_go();
        String line = "";
        if (Client_.line.get("result").equals("Server_error")) {
            this.result_cub.setText("Error");
            this.er4.setText("Отсутствует соединение с сервером");
            this.er4.setLayoutX(244.0D);
            err4 = true;
            this.er4.setVisible(true);
        } else if (Client_.line.get("result").equals("Error")) {
            this.result_cub.setText("Error");
            this.er4.setText("Некорректный ввод данных");
            this.er4.setLayoutX(264.0D);
            err4 = true;
            this.er4.setVisible(true);
        } else {
            JSONArray jsonArray = (JSONArray)Client_.line.get("result");

            for(int i = 0; i < jsonArray.length(); ++i) {
                String result = String.format("x = %.5f", Double.parseDouble(jsonArray.get(i).toString()));
                int point_index = result.indexOf(",") + 1;
                int number_of_zeros = 0;

                for(int j = point_index; j < result.length(); ++j) {
                    if (Character.getNumericValue(result.charAt(j)) == 0) {
                        ++number_of_zeros;
                    } else {
                        number_of_zeros = 0;
                    }
                }

                if (String.format("%." + (5 - number_of_zeros) + "f", Double.parseDouble(jsonArray.get(i).toString())).equals("0")) {
                    line = line + "x = " + Client_.line.get("result").toString();
                } else {
                    line = line + String.format("x = %." + (5 - number_of_zeros) + "f", Double.parseDouble(jsonArray.get(i).toString()));
                }

                if (i != jsonArray.length() - 1) {
                    line = line + ";  ";
                }

                if (i == jsonArray.length() - 1) {
                    line.length();
                    if (line.length() > 42) {
                        this.result_cub.setAlignment(Pos.CENTER_LEFT);
                        this.result_cub.setDisable(false);
                    }
                }
            }

            this.result_cub.setText(line);
        }

    }

    @FXML
    private void clearing1() {
        this.res_1.setText("");
        if (this.textFields != null) {
            TextField[][] var1 = this.textFields;
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                TextField[] t = var1[var3];
                TextField[] var5 = t;
                int var6 = t.length;

                for(int var7 = 0; var7 < var6; ++var7) {
                    TextField t1 = var5[var7];
                    t1.setText("");
                }
            }
        }

        if (err1) {
            this.er1.setVisible(false);
            err1 = false;
        }

        if (s_err1) {
            this.server_error1.setVisible(false);
            s_err1 = false;
        }

        this.res_1.setAlignment(Pos.CENTER);
        this.res_1.setDisable(true);
    }

    @FXML
    private void clearing2() {
        this.solve_integral.setText("");
        this.a.setText("");
        this.b.setText("");
        this.integral.setText("");
        if (err2) {
            this.er2.setVisible(false);
            err2 = false;
        }

        this.solve_integral.setAlignment(Pos.CENTER);
        this.solve_integral.setDisable(true);
    }

    @FXML
    private void clearing3() {
        this.result_nolinear_ur.setText("");
        this.x0.setText("");
        this.tochnost.setText("");
        this.nolinear_ur.setText("");
        if (err3) {
            this.er3.setVisible(false);
            err3 = false;
        }

        this.result_nolinear_ur.setAlignment(Pos.CENTER);
        this.result_nolinear_ur.setDisable(true);
    }

    @FXML
    private void clearing4() {
        this.result_cub.setText("");
        this.cub_d.setText("");
        this.cub_c.setText("");
        this.cub_b.setText("");
        this.cub_a.setText("");
        if (err4) {
            this.er4.setVisible(false);
            err4 = false;
        }

        this.result_cub.setAlignment(Pos.CENTER);
        this.result_cub.setDisable(true);
    }
}
