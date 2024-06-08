(ns main.app
  (:require [reagent.core :as reagent]
            [reagent.dom  :as reagent-dom]
    
            ["@mantine/core" :refer [MantineProvider]]
            
            ["wagmi" :as wagmi]
            ["wagmi/chains" :refer [mainnet base]]
            ["wagmi/connectors" :refer [injected metaMask safe walletConnect]]
            ["@tanstack/react-query" :as tanstack]
            
            [main.component :refer [main theme]]))

(def alchemy-api-key "8mdS5ri8-CcW97UmRxtCuyKV3I2JuWQc")
(def project-id "a1f553a67e9967aba78bc770c739bd61")

(defonce query-client (new tanstack/QueryClient))

(def config 
  (wagmi/createConfig 
    #js {:chains #js [mainnet base]
         :connectors #js [(injected) (walletConnect #js {:projectId project-id}) (metaMask) (safe)]
         :transports #js {"1" (wagmi/http) "8453" (wagmi/http)}}))

(defn bundle []
  [:> MantineProvider {:theme theme}
     [:> wagmi/WagmiProvider {:config config}
        [:> tanstack/QueryClientProvider {:client query-client}
           [:f> main]
           ]]])

(defn render []
  (reagent-dom/render [bundle] (.getElementById js/document "app"))
  )

(defn ^:export init []
  (render)
  )
