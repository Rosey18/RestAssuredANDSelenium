import java.util.Calendar;

public class FormatType
{
    public static void main(String[] args) {

        //форматирование чисел
        String s = String.format("%,.2f", 1000000.87153); //преваращение числового значения в строку
        System.out.println(s);
        String g = String.format("%,d", 452685198); //возвращает отформатированную строку, используя указанную строку формата и аргументы
        System.out.println(g);

        int one = 20458;
        double two = 1146546.7829;
        String i = String.format("Уровень %d из %,.2f", one, two);
        System.out.println(i);

        String l = "25";
        int st_int = Integer.parseInt(l); //превращение числа в строку
        System.out.println(l);


        //форматируем даты
        Calendar c = Calendar.getInstance();
        c.set(2020,4,2,12,18);
        long day1 = c.getTimeInMillis();
        day1 += 1000 * 120 * 60;
        c.setTimeInMillis(day1);
        System.out.println("Новый час: - " + c.get(c.HOUR_OF_DAY));
        c.add(c.DATE, 29);
        System.out.println("Добавить 29 дней: - " + c.getTime());
        c.roll(c.DATE, 27);
        System.out.println("Прокрутить на 27 дней: - " + c.getTime());
        c.set(c.DATE, 7);
        System.out.println("Установить дату в 7: - " + c.getTime());
    }
}
