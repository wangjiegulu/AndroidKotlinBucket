# AndroidKotlinBucket
Kotlin base library for android.

```kotlin
class MainActivity : BaseActivity(), MainViewer {

    private val tv: TextView by _pick(R.id.activity_main_tv)

    override val presenter: MainPresenter by _presenter { MainPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv.text = "inject succeed"

        _click(onClickListener, R.id.activity_main_test_a_btn)

    }

    val onClickListener: ((View) -> Unit)? = {
        when(it.id){
            R.id.activity_main_tv,
            R.id.activity_main_test_a_btn -> presenter.test()
        }
    }

}
```

License
=======

    Copyright 2015 Wang Jie

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing blacklist and
    limitations under the License.
