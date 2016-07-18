# untangled-starter

[![Clojars Project](https://img.shields.io/clojars/v/untangled-starter.svg)](https://clojars.org/untangled-starter)

[ReactBootstrap](https://react-bootstrap.github.io/) wrapper macros and some syntactical sugar for Om Next.

You can use the macros defined in `untangled-starter.bootstrap :as bs` and
freely mix [Sablono](https://github.com/r0man/sablono) and ReactBootstrap with ClojureScript-y API.

For example, a navbar could look like this.
```cljs
(bs/navbar
  (bs/nav
    (bs/nav-item {:event-key 1} "NavItem 1 content")
    (bs/nav-item {:event-key 2 :title "Item"} "NavItem 2 content")
    (bs/nav-dropdown {:event-key 4 :title "Dropdown" :id "basic-nav-dropdown"}
      (bs/menu-item {:event-key "3.1"} [:span.action "Action"])
      (bs/menu-item {:event-key "3.1"} [:span.action "Another Action"])
      (bs/menu-item {:divider true})
      (bs/menu-item {:event-key "3.1"} [:span.link "Separated Link"]))))
```

With `untangled-starter.aum` you get a concise syntax for creating Om Next components
and their associated factory functions with a sablono compiler wrapping the render body.

For example, the following two are roughly equivalent.

```cljs
(defcomponent Widget {:keyfn :id}
  (ident [this props]
    [:widget/by-id (:id props)])
  (query [this]
    [:id :name])
  (render [this]
    (let [{:keys [name]} (om/props this)
          {:keys [activate-fn]} (om/get-computed this)]
      [:div.widget-class
        (bs/button {:on-click #(activate-fn (om/get-ident this))}
          name)])))
```

```cljs
(defui Widget
  static om/Ident
  (ident [this props]
    [:widget/by-id (:id props)])
  static om/IQuery
  (query [this]
    [:id :name])
  Object
  (render [this]
    (dom/li nil
      (let [{:keys [name]} (om/props this)
            {:keys [activate-fn]} (om/get-computed this)]
        (dom/div #js {:className "widget-class"}
          (dom/a #js {:onClick #(activate-fn (om/get-ident this))}
            name))))))

(def ui-widget (om/factory Widget {:keyfn :id}))
```
