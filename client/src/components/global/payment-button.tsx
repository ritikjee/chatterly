import { Button } from "@/components/ui/button";

import { CreditCardIcon } from "lucide-react";

type Props = {};

const PaymentButton = (props: Props) => {
  return (
    <Button
      className="bg-gradient-to-br
     text-white 
     rounded-full 
    from-[#6d60a3] 
    via-[#9434E6] 
    font-bold 
    to-[#CC3BD4]"
    >
      <CreditCardIcon />
      Upgrade
    </Button>
  );
};

export default PaymentButton;
