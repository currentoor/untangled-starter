(ns untangled-starter.aum
  #?@(:clj [(:require [sablono.core :as s]
                      [camel-snake-kebab.core :as csk]
                      [om.next :as om])]
      :cljs [(:require [sablono.core])
             (:require-macros [untangled-starter.aum])]))

#?(:clj (defn compile-render-body [[_ args & render-body :as render-form]]
          `(~'render ~args
            ~@(butlast render-body)
            (s/html ~(last render-body)))))

#?(:clj (defn prepend-protocol [[form-name :as form]]
          (case form-name
            ident                 `(~'static om.next/Ident ~form)
            params                `(~'static om.next/IQueryParams ~form)
            query                 `(~'static om.next/IQuery ~form)
            -set-state!           `(om.next/ILocalState ~form)
            -get-state            `(om.next/ILocalState ~form)
            -get-rendered-state   `(om.next/ILocalState ~form)
            -merge-pending-state! `(om.next/ILocalState ~form)
            render                `(~'Object ~(compile-render-body form))
            `(~form)              ; No protocol specified, rely on previous specification.
            )))

#?(:clj (defmacro defcomponent
          "Wrapps the body of a render function, in an Om.next component,
  in a sablono compiler/interpreter call. Adds protocol names for various
  om.next protocol implementations. Also defines a snake-cased factory function
  for the component."
          [& [class-symbol factory-args & protocol-forms]]
          `(do
             (om.next/defui ~class-symbol
               ~@(mapcat prepend-protocol protocol-forms))

             (def ~(csk/->kebab-case-symbol (->> class-symbol
                                                 (str "Ui")
                                                 symbol))
               (om.next/factory ~class-symbol ~factory-args)))))
