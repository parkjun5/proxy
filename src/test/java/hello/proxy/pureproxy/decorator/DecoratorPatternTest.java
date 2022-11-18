package hello.proxy.pureproxy.decorator;

import hello.proxy.pureproxy.decorator.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class DecoratorPatternTest {

    @Test
    void noDecorator() {
        Component realComponent = new RealComponent();
        DecoratorPatternClient client = new DecoratorPatternClient(realComponent);
        client.execute();
    }

    @Test
    void decorateMessageTest() {
        Component realComponent = new RealComponent();
        Component messageComponent = new MessageDecorator(realComponent);
        DecoratorPatternClient client = new DecoratorPatternClient(messageComponent);
        client.execute();
    }

    @Test
    void decorateMessageTimeTest() {
        Component realComponent = new RealComponent();
        Component messageComponent = new MessageDecorator(realComponent);
        Component timeDecoratorComponent = new TImeDecorator(messageComponent);
        DecoratorPatternClient client = new DecoratorPatternClient(timeDecoratorComponent);
        client.execute();
    }
}
