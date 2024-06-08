import * as i0 from "react";
import * as i1 from "react-dom";
import * as i2 from "@mantine/core";
import * as i3 from "wagmi";
import * as i4 from "wagmi/chains";
import * as i5 from "wagmi/connectors";
import * as i6 from "@tanstack/react-query";
import * as i7 from "@mantine/hooks";

const ALL = {};

globalThis.shadow$bridge = function(name) {
  const ret = ALL[name];
  if (ret == undefined) {
    throw new Error("Dependency: " + name + " not provided by external JS!");
  } else {
    return ret;
  }
};

ALL["react"] = i0;

ALL["react-dom"] = i1;

ALL["@mantine/core"] = i2;

ALL["wagmi"] = i3;

ALL["wagmi/chains"] = i4;

ALL["wagmi/connectors"] = i5;

ALL["@tanstack/react-query"] = i6;

ALL["@mantine/hooks"] = i7;
