(ns main.app
  (:require ["@mantine/core" :refer [MantineProvider]]
            ["react-dom/client" :refer [createRoot]]
               
            [reagent.core :as reagent]
            [reagent.dom  :as reagent-dom]
            
            ["wagmi" :as wagmi]
            ["wagmi/chains" :refer [mainnet base]]
            ["wagmi/connectors" :refer [injected metaMask safe walletConnect]]
            ["@tanstack/react-query" :as tanstack]
            [helix.core :refer [$]]
            [main.component :refer [main app-shell theme]]
            [main.lib :refer [defnc]]))

(def alchemy-api-key "8mdS5ri8-CcW97UmRxtCuyKV3I2JuWQc")
(def project-id "a1f553a67e9967aba78bc770c739bd61")

(def mainnet-id (.-id mainnet))
(def base-id (.-id base))

(defonce query-client (new tanstack/QueryClient))

(def config (wagmi/createConfig
              #js
              {:chains #js [mainnet base]
               :connectors #js [(injected) (walletConnect #js {:projectId project-id}) (metaMask) (safe)]
               :transports #js {"1" (wagmi/http) "8453" (wagmi/http)}
               }))

(defnc app []
  ($ MantineProvider {:theme theme}
     ($ wagmi/WagmiProvider {:config config}
        ($ tanstack/QueryClientProvider {:client query-client}
           ($ app-shell)))))

(defn bundle []
  [:> MantineProvider {:theme theme}
     [:> wagmi/WagmiProvider {:config config}
        [:> tanstack/QueryClientProvider {:client query-client}
           [:f> main]
           ]]])

(defonce root
  (createRoot
   (js/document.getElementById "app")))

(defn render []
  ;(.render root ($ app))
  (reagent-dom/render [bundle] (.getElementById js/document "app"))
  )

(defn ^:export init []
  (render))
