package com.github.crayonxiaoxin.lib_processor;

import com.github.crayonxiaoxin.lib_annotations.BindView;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

public class BindProcessor extends AbstractProcessor {

    Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        System.out.println("BindProcessor Run!!!");
        filer = processingEnv.getFiler();
    }

    /**
     * 防止版本冲突
     * @return
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("BindProcessor process!!!");
        // 遍历 class
        for (Element rootElement : roundEnv.getRootElements()) {
            String packageStr = rootElement.getEnclosingElement().toString();
            String clazzStr = rootElement.getSimpleName().toString();
            // 生成的 class 名称
            ClassName className = ClassName.get(packageStr, clazzStr + "$Binding");
            // 构造函数
            MethodSpec.Builder constructorBuilder = MethodSpec.constructorBuilder()
                    .addModifiers(Modifier.PUBLIC)
                    .addParameter(ClassName.get(packageStr, clazzStr), "activity");
            boolean hasBinding = false;
            for (Element enclosedElement : rootElement.getEnclosedElements()) {
                if (enclosedElement.getKind() == ElementKind.FIELD) {
                    BindView bindView = enclosedElement.getAnnotation(BindView.class);
                    // 找到 BindView，为构造函数追加方法体
                    if (bindView != null) {
                        hasBinding = true;
                        constructorBuilder.addStatement(
                                "activity.$N = activity.findViewById($L)",
                                enclosedElement.getSimpleName(),
                                bindView.value()
                        );
                    }
                }
            }

            // 构造 class
            TypeSpec builtClazz = TypeSpec.classBuilder(className)
                    .addModifiers(Modifier.PUBLIC)
                    .addMethod(constructorBuilder.build())
                    .build();
            // 生成 class 文件
            if (hasBinding) {
                try {
                    JavaFile.builder(packageStr, builtClazz).build().writeTo(filer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(BindView.class.getCanonicalName());
    }
}