export enum IntegrationType {
  INSTAGRAM = "INSTAGRAM",
}

export type INTEGRATIONS ={
  id: string;
  name: IntegrationType;
  createdAt: string;
  userId: string;
  token: string;
  expiresAt?: string;
  instagramId?: string;
}
