(ns untangled-starter.bootstrap
  #?@(:clj [(:require
             [camel-snake-kebab.core :as csk]
             [camel-snake-kebab.extras :as csk.extras]
             [sablono.core :as s])]
      :cljs [(:require
              [cljsjs.react-bootstrap]
              [sablono.core])
             (:require-macros
              [untangled-starter.bootstrap])]))

#?(:clj (def ^:private class-symbols
          '[Accordion
            Affix
            AffixMixin
            Alert
            Badge
            BootstrapMixin
            Breadcrumb
            BreadcrumbItem
            Button
            ButtonGroup
            ButtonInput
            ButtonToolbar
            Carousel
            CarouselItem
            Col
            CollapsibleNav
            Dropdown
            DropdownButton
            Glyphicon
            Grid
            Image
            Input
            Interpolate
            Jumbotron
            Label
            ListGroup
            ListGroupItem
            MenuItem
            Modal
            ModalBody
            ModalFooter
            ModalHeader
            ModalTitle
            Nav
            Navbar
            NavBrand
            NavDropdown
            NavItem
            Overlay
            OverlayTrigger
            PageHeader
            PageItem
            Pager
            Pagination
            Panel
            PanelGroup
            Popover
            ProgressBar
            ResponsiveEmbed
            Row
            SafeAnchor
            SplitButton
            SubNav
            Tab
            Table
            Tabs
            Thumbnail
            Tooltip
            Well
            Collapse
            Fade
            FormControls]))

#?(:clj (defn compile-child [child]
     `(sablono.core/html ~child)))

#?(:clj (defn ^:private class-symbol->constructor [class-symbol]
          `(defmacro ~(csk/->kebab-case-symbol class-symbol) [& args#]
             (let [[attrs# & [children#]] (if (or (-> args# first map?)
                                                  (-> args# first nil?))
                                            [(first args#) (rest args#)]
                                            [nil args#])
                   attrs# (csk.extras/transform-keys csk/->camelCaseString attrs#)]
               `(js/React.createElement
                 ~(symbol "js" (str "ReactBootstrap." (name '~class-symbol)))
                 (cljs.core/clj->js ~attrs#)
                 ~@(map compile-child children#))))))

#?(:clj (defmacro ^:private def-constructors []
          `(do
             ~@(clojure.core/map class-symbol->constructor class-symbols))))

#?(:clj (def-constructors))
