/*import javax.xml.stream.Location;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class GameHelper {
    private static final String alphabet = "abcdefg";
    private int gridLength = 7;
    private int gridSize = 49;
    private int[] grid = new int[gridSize];


    public String getUserInput(String prompt) {
        String inputLine = null;
        System.out.println(prompt + " ");
        try {
            BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
            inputLine = is.readLine();
            if (inputLine.length() == 0) return null;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
        return inputLine.toLowerCase();
    }

    public ArrayList<String> placeDotCom(int comSize) {
        ArrayList<String> alphaCells = new ArrayList<String>();
        String[] alphacoords = new String[comSize]; //хранит координаты типа f6
        String temp = null; //временная строка для конкатенции
        int[] coords = new int[comSize]; //счтечик текщего "сайт"
        int attempts = 0; //счетчки текущих попыток
        boolean success = false; //нашли местоположение?
        int location = 0; //текущее начальное местоположение

        comCount++; //энный "сайт"
        int incr = 1; //устанавливаем горизантальный инкеремент
        if ((comCount % 2) == 1) { //если нечентный (размещаем вертикально)
            incr = gridLength; //устанавливаем вертикальный инкеремент
        }
        while (!success & attempts++ < 200) { //главный поисковой цикл (32)
            location = (int) (Math.random() * gridSize); //получаем случайную стратовую точку
            //System.out.println("try" + location);
            int x = 0; //энная позиция в "сайте", который нужно разместить
            success = true; //предполоагаем успешный исход
            while (success && x < comSize) { //ищем соседнюю неиспользованную ячейку
                if (grid[location] == 0) { //если еще не используется
                    coords[x++] = location; //сохраняем местоположение
                    location += incr; // пробуем "следующую" соседнюю ячейку
                    if (location >= gridSize) { //вышли из рамки вниз
                        success = false; //неудача
                    }
                    if (x > 0 && (location % gridLength == 0)) { //вышли из рамки правый край
                        success = false; //неудача
                    }
                } else { //нашли уже использо=ующееся местоположение
                    //System.out.println("try" + location);
                    success = false; //неудача
                }
            }
        } //конец уайла

            int x = 0; //переводим местоположение в символьные координаты
            int row = 0;
            int column = 0;
            //System.out.println("/n");
            while (x < comSize) {
                grid[coords[x]] = 1; //помечаем ячейки на главный сетке как "использованные"
                row = (int) (coords[x] / gridLength); //получаем значение строки
                column = coords[x] % gridLength; //получаем числовое значение столбца
                temp = String.valueOf(alphabet.charAt(column)); //преобразуем его в строковый символ

                alphaCells.add(temp.concat(Integer.toString(row)));
                x++;
                //System.out.println("  coord "+x+" = " + alphaCells.get(x-1));
            }
            //System.out.println("/n");
            return alphaCells;
        }
    }
*/

