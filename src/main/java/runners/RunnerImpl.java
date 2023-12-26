package runners;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import api.AfterEach;
import api.BeforeEach;
import api.Before;
import api.After;

import api.Test;


public class RunnerImpl {
    public void run(Class<?> testClass) throws Exception
    {
        List<Method> beforeMethods = getMethodsByAnnotation(testClass,Before.class);
        List<Method> afterMethods = getMethodsByAnnotation(testClass,After.class);
        List<Method> beforeEachMethods = getMethodsByAnnotation(testClass,BeforeEach.class);
        final List<Method> afterEachMethods = getMethodsByAnnotation(testClass,AfterEach.class);
        List<Method> testMethods = getMethodsByAnnotation(testClass,Test.class);
        Object testInstance = testClass.getDeclaredConstructor().newInstance();
        executeMethods(beforeMethods,testInstance);
        for (Method testMethod : testMethods) {
            executeMethods(beforeEachMethods, testInstance);
            try {
                testMethod.invoke(testInstance);
                System.out.println("Test Passed: " + testMethod.getName());
            } catch (Exception e) {
                System.out.println("Test Failed: " + testMethod.getName());
                e.printStackTrace();
            }
            executeMethods(afterEachMethods, testInstance);
        }

        executeMethods(afterMethods, testInstance);
    }
    private List<Method> getMethodsByAnnotation(Class<?> testClass, Class<? extends Annotation> annotation) {
        List<Method> annotatedMethods = new ArrayList<>();
        for (Method method : testClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(annotation)) {
                annotatedMethods.add(method);
            }
        }
        return annotatedMethods;
    }

    private void executeMethods(List<Method> methods,Object testinstance)
    {
        for (Method method : methods) {
            try {
                method.invoke(testinstance);
            } catch (Exception e) {
                throw new RuntimeException("Error executing method: " + method.getName(), e);
            }
        }
    }
}
