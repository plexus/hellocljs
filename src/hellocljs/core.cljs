(ns hellocljs.core)

;; ============================================================
;; JavaScript global objects such as window and document are availble
;; under the js namespace

;; window   => js/window
;; document => js/document

;; ============================================================
;; Calling a javascript function on an object is done
;; with (.function-name object), e.g.

;; document.write("Hello, Clojurescript!")

(defn hello-clojurescript
  "A simple Hello, World! to warm up."
  []
  (.write js/document "Hello, ClojureScript!"))

;; (hello-clojurescript)

;; ============================================================
;; Let's do some DOM manipulation. We create a <div>, stick some text
;; inside, add it to the DOM

;; var el = document.createElement('div')
;; var txt = document.createTextNode("Hello, div")

(defn vanilla-dom-create-div
  "Use the DOM API to create a div with some text"
  []
  (let [el  (.createElement  js/document "div")
        txt (.createTextNode js/document "This textnode will end up inside the div element we created earlier. Yay \\o/.")]

    ;; el.appendChild(txt)
    (.appendChild el txt)

    ;; Here body is not a function, we use the .-property syntax to
    ;; retrieve the value of the property, instead of calling it as a
    ;; function

    ;; document.body.appendChild(el)
    (.appendChild (.-body js/document) el)))

;; (vanilla-dom-create-div)
