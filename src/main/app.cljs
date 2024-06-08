(ns main.app
  (:require [reagent.core :as reagent]
            [reagent.dom  :as reagent-dom]
    
            ["@mantine/core" :refer [MantineProvider]]
            
            ["wagmi" :as wagmi]
            ["wagmi/chains" :refer [mainnet base]]
            ["wagmi/connectors" :refer [injected metaMask safe walletConnect]]
            ["@tanstack/react-query" :as tanstack]

            ["../ecmascript/config" :refer [Config]]
            
            [main.component :refer [main theme]]))

(defonce query-client (new tanstack/QueryClient))

(defn bundle []
  [:> MantineProvider {:theme theme}
     [:> wagmi/WagmiProvider {:config Config}
        [:> tanstack/QueryClientProvider {:client query-client}
           [:f> main]
           ]]])

(defn render []
  (reagent-dom/render [bundle] (.getElementById js/document "app"))
  )

(defn ^:export init []
  (render)
  )
