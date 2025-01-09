"use client";

import { usePaths } from "@/hooks/use-nav";
import { useQueryAutomations } from "@/hooks/user-queries";

type Props = {};

const AutomationList = (props: Props) => {
  const { pathname } = usePaths();

  const { data } = useQueryAutomations();

  console.log(data);

  return <div className="flex flex-col gap-y-3"></div>;
};

export default AutomationList;
