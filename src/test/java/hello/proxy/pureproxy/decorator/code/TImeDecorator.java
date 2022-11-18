package hello.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TImeDecorator implements Component {

    private Component component;

    public TImeDecorator(Component component) {
        this.component = component;
    }

    @Override
    public String operation() {
        log.info("TImeDecorator 실행");
        long startTime = System.currentTimeMillis();

        String operation = component.operation();

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("TimeDecorator 종료 resultTime={}ms", resultTime);
        return operation;
    }
}
