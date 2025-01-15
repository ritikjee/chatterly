export enum SubscriptionPlan {
  PRO = "PRO",
  FREE = "FREE",
}

export type SUBSCRIPTION = {
  id: string;
  userId: string;
  createdAt: string;
  plan: SubscriptionPlan;
  updatedAt: string;
  customerId?: string;
};
