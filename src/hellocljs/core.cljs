(ns hellocljs.core
  (:require [domina :refer [append! set-styles! log]]
            [domina.events :refer [listen! target]]
            [domina.css :refer [sel]]))

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

;; ============================================================
;; An example using domina. These are the functions we're using

;; domina.css/sel
;;   Use a CSS selector to select elements

;; domina/append!
;;   Append HTML to a DOM element

;; domina/set-styles!
;;   Set the style of DOM elements

;; domina.events/listen!
;;   Listen to browser events
;;   (listen elements event handler)

;; domina.events/target
;;   Extract the "target", that is, the DOM element that caused
;;   the event, from an event object


;; We're using (doseq ...) instead of (for ...) because we only care
;; about the side effects of the iteration.

(defn rand-color []
  (rand-nth ["blue" "red" "green" "yellow" "magenta" "cyan" "brown"]))

(defn set-random-background-color [event]
  (set-styles! (target event) {:background-color (rand-color)}))

(defn make-squares
  "Use domina to create a 1000 squares, and add an event handler to
  change the color on mouseover"
  []
  (dotimes [_ 1000]
    (append! (sel "body") "<div class='square'></div>"))

  ;; Set up a "mouseover" event handler on each square
  ;; when the event handler fires, we change the color of the square
  (listen! (sel ".square") :mouseover set-random-background-color))

(make-squares)
