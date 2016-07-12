[Link](http://www.jianshu.com/p/5fc2b3362702)
#lambda表达式
```
//1.用lambda表达式实现Runnable
        /*(params) -> expression
          (params) -> statement
          (params) -> { statements }
                */

        new Thread(new Runnable() {
            @Override
            public void run() {
                printLog("Before Java8, too much code for too little to do");
            }
        }).start();


        new Thread(() -> {
            printLog("In Java8, Lambda expression rocks !!");
        }).start();
```

```
 2.用lambda表达式进行事件处理

        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        testButton.setOnClickListener((v) -> {

        });
```
#函数式接口
#流API
#默认方法
#新的Date
#Time API
