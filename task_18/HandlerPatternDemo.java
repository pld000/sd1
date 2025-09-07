import java.util.*;

/**
 * Паттерн "Handler" - система обработки разных типов задач
 * Реализует единный интерфейс для обработки разных типов операций
 */

interface Handler<T> {
    boolean canHandle(String type);
    T handle(Object data);
    String getHandlerName();
}

class HandlerManager {
    private List<Handler<?>> handlers = new ArrayList<>();

    public void addHandler(Handler<?> handler) {
        handlers.add(handler);
    }

    public Object process(String type, Object data) {
        for (Handler<?> handler : handlers) {
            if (handler.canHandle(type)) {
                System.out.println("Используем обработчик: " + handler.getHandlerName());
                return handler.handle(data);
            }
        }
        return "Нет подходящего обработчика для типа: " + type;
    }
}

class TextHandler implements Handler<String> {
    @Override
    public boolean canHandle(String type) {
        return "text".equals(type);
    }

    @Override
    public String handle(Object data) {
        String text = (String) data;
        return "Обработанный текст: " + text.toUpperCase();
    }

    @Override
    public String getHandlerName() {
        return "TextHandler";
    }
}

class NumberHandler implements Handler<Double> {
    @Override
    public boolean canHandle(String type) {
        return "number".equals(type);
    }

    @Override
    public Double handle(Object data) {
        if (data instanceof String) {
            return Double.parseDouble((String) data) * 2;
        }
        if (data instanceof Number) {
            return ((Number) data).doubleValue() * 2;
        }
        return 0.0;
    }

    @Override
    public String getHandlerName() {
        return "NumberHandler";
    }
}

class ListHandler implements Handler<String> {
    @Override
    public boolean canHandle(String type) {
        return "list".equals(type);
    }

    @Override
    public String handle(Object data) {
        @SuppressWarnings("unchecked")
        List<Object> list = (List<Object>) data;
        return "Размер списка: " + list.size() + ", элементы: " + list.toString();
    }

    @Override
    public String getHandlerName() {
        return "ListHandler";
    }
}

public class HandlerPatternDemo {
    public static void main(String[] args) {
        HandlerManager manager = new HandlerManager();
        manager.addHandler(new TextHandler());
        manager.addHandler(new NumberHandler());
        manager.addHandler(new ListHandler());

        System.out.println("1. " + manager.process("text", "hello world"));
        System.out.println("2. " + manager.process("number", "42"));
        System.out.println("3. " + manager.process("number", 15));

        List<Object> testList = Arrays.asList("яблоко", "банан", 123);
        System.out.println("4. " + manager.process("list", testList));

        System.out.println("5. " + manager.process("unknown", "что-то"));
    }
}
