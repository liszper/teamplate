import * as i0 from "react-dom";
import * as i1 from "@mantine/core";
import * as i2 from "wagmi";
import * as i3 from "react-dom/client";
import * as i4 from "@tanstack/react-query";
import * as i5 from "wagmi/chains";
import * as i6 from "react-refresh/runtime";
import * as i7 from "react/jsx-runtime";
import * as i8 from "wagmi/connectors";
import * as i9 from "@mantine/hooks";
import * as i10 from "react";

const ALL = {};

globalThis.shadow$bridge = function(name) {
  const ret = ALL[name];
  if (ret == undefined) {
    throw new Error("Dependency: " + name + " not provided by external JS!");
  } else {
    return ret;
  }
};

ALL["react-dom"] = i0;

ALL["@mantine/core"] = i1;

ALL["wagmi"] = i2;

ALL["react-dom/client"] = i3;

ALL["@tanstack/react-query"] = i4;

ALL["wagmi/chains"] = i5;

ALL["react-refresh/runtime"] = i6;

ALL["react/jsx-runtime"] = i7;

ALL["wagmi/connectors"] = i8;

ALL["@mantine/hooks"] = i9;

ALL["react"] = i10;
