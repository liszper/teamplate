(ns main.component
  (:require ["@mantine/core" :refer [AppShell Burger Button createTheme Group
                                     Skeleton]]
            ["@mantine/hooks" :refer [useDisclosure]]
            ["react" :as react]
            ["wagmi" :as wagmi]))

(def theme
  (createTheme
   (clj->js
    {:colors {:deep-blue ["#eef3ff" "#dce4f5" "#b9c7e2" "#94a8d0" "#748dc1" "#5f7cb8" "#5474b4" "#44639f" "#39588f" "#2d4b81"]
              :yellow-king ["#fafae8" "#f0f1da" "#e0e1b9" "#cfd094" "#c0c274" "#b7b95f" "#b2b454" "#9c9e44" "#8a8c39" "#767a2b"]}
     :shadows {:md "1px 1px 3px rgba(0, 0, 0, .25)"
               :xl "5px 5px 3px rgba(0, 0, 0, .25)"}
     :headings {:fontFamily "Roboto, sans-serif"
                :sizes {:h1 {:fontSize "rem(36)"}}}})))

(defn main []
  (react/useEffect
    (fn [] 
      (js/console.log "Main component rendered..")
      )) 
  (let [address (.-address (wagmi/useAccount))
        connect (.-connect (wagmi/useConnect))
        connectors (.-connectors (wagmi/useConnect))]
    [:> AppShell {:padding "md"
                  :header #js {:height 60}
                  :navbar #js {:width 300
                               :breakpoint "md"
                               ;:collapsed #js {:mobile (not opened)}
                               }}

       [:> (.-Header AppShell)
          [:> Group {:h "100%" :px "md"}
             [:> Burger {;:opened opened
                         ;:onClick toggle
                         :hiddenFrom "sm"
                         :size "sm"}]
              [:div "Onchain Metagame"]]]

       [:> (.-Navbar AppShell) {:p "md"}
           (for [index (range 10)]
             [:> Skeleton {:key index :h 28 :mt "sm" :animate false}])]

       [:> (.-Main AppShell)
           (str (js->clj address))
           [:h1 "Welcome to the Onchain Metagame!"]

           (for [connector connectors]
             [:> Button 
              {:key (.-id connector)
               :onClick #(connect #js {:connector connector})
               :color "yellow-king"}
              (.-name connector)])
          
          ]]))
