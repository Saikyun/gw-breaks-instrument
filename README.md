# example of ghostwheel breaking spec instrumentation

```
$ yarn
$ shadow-cljs watch node-gw node-spec
```

In a different terminal
```
$ node out/gw.js
$ node out/spec.js
```

Expected result is both throwing an error, because I'm erronously calling `ranged-rant`
 with `start` higher than `end`. However, this is what I get:
 
 (TLDR; with ghostwheel, no exception, without ghostwheel, I get the expected exception)

```
~/programmering/clojure/test-ghostwheel $ node out/gw.js 
Checking starter.ghostwheel ...
Passed all 1 checks
4
shadow-cljs - #6 ready!
interrupt: 2
~/programmering/clojure/test-ghostwheel $ node out/spec.js 
SHADOW import error /Users/jona/programmering/clojure/test-ghostwheel/.shadow-cljs/builds/node-spec/dev/out/cljs-runtime/shadow.module.main.append.js

/Users/jona/programmering/clojure/test-ghostwheel/.shadow-cljs/builds/node-spec/dev/out/cljs-runtime/cljs/core.cljs:11332
(defn ^{:jsdoc ["@constructor"]}
^
Error: Call to #'starter.spec/ranged-rand did not conform to spec.
    at new cljs$core$ExceptionInfo (/Users/jona/programmering/clojure/test-ghostwheel/.shadow-cljs/builds/node-spec/dev/out/cljs-runtime/cljs/core.cljs:11332:1)
    at Function.cljs$core$IFn$_invoke$arity$3 (/Users/jona/programmering/clojure/test-ghostwheel/.shadow-cljs/builds/node-spec/dev/out/cljs-runtime/cljs/core.cljs:11361:1)
    at Function.cljs$core$IFn$_invoke$arity$2 (/Users/jona/programmering/clojure/test-ghostwheel/.shadow-cljs/builds/node-spec/dev/out/cljs-runtime/cljs/core.cljs:11361:1)
    at conform_BANG_ (/Users/jona/programmering/clojure/test-ghostwheel/.shadow-cljs/builds/node-spec/dev/out/cljs-runtime/cljs/spec/test/alpha.cljs:92:24)
    at conform_BANG__STAR_ (/Users/jona/programmering/clojure/test-ghostwheel/.shadow-cljs/builds/node-spec/dev/out/cljs-runtime/cljs/spec/test/alpha.cljs:87:3)
    at Object.G__42922__delegate (/Users/jona/programmering/clojure/test-ghostwheel/.shadow-cljs/builds/node-spec/dev/out/cljs-runtime/cljs/spec/test/alpha.cljs:118:19)
    at Object.G__42922 [as ranged_rand] (/Users/jona/programmering/clojure/test-ghostwheel/.shadow-cljs/builds/node-spec/dev/out/cljs-runtime/cljs/spec/test/alpha.cljs:116:22)
    at Function.starter$spec$init (/Users/jona/programmering/clojure/test-ghostwheel/.shadow-cljs/builds/node-spec/dev/out/cljs-runtime/starter/spec.cljs:17:1)
    at Function.cljs$core$IFn$_invoke$arity$2 (/Users/jona/programmering/clojure/test-ghostwheel/.shadow-cljs/builds/node-spec/dev/out/cljs-runtime/cljs/core.cljs:3912:8)
    at Function.cljs$core$IFn$_invoke$arity$2 (/Users/jona/programmering/clojure/test-ghostwheel/.shadow-cljs/builds/node-spec/dev/out/cljs-runtime/cljs/core.cljs:3948:6)
```

If I try to add a call to `clojure.spec.test.alpha/instrument` manually in the ghostwheel code, I get an compilation error:
```
[:node-gw] Build failure:
------ ERROR -------------------------------------------------------------------
 File: /Users/jona/programmering/clojure/test-ghostwheel/src/starter/ghostwheel.cljs:15:1
--------------------------------------------------------------------------------
  12 |   (println (ranged-rand 10 0)))
  13 | 
  14 | (g/check)
  15 | (st/instrument)
-------^------------------------------------------------------------------------
Error in phase :compilation
Unable to resolve var: >defn- in this context at line 15 starter/ghostwheel.cljs
--------------------------------------------------------------------------------
```
