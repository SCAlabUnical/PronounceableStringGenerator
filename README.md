# PronounceableStringGenerator
This Java package allows to generate pronounceable random strings.

Just create an object `PronounceableStringGenerator` and invoke the method `generate`:

```Java
PronounceableStringGenerator mg = new PronounceableStringGenerator();
Random ran = new Random(4782);
System.out.println(mg.generate(ran, 8));
System.out.println(mg.generate(ran, 8));
System.out.println(mg.generate(ran, 8));
```
