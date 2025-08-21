export abstract class DeliveryOrderBase {
  @Output() onNext = new EventEmitter<{ deliveryOrder: IDeliveryOrder, saveCurrentDelivery?: boolean }>();

  deliveryOrder: DeliveryOrder;

  abstract getCalendar(fromDate: Date, prevCalendar?: DeliveryCalendar): Promise<DeliveryCalendar>;

  abstract confirmDelivery(): void;
}

export class VendorDelivery extends DeliveryOrderBase {
  pickupOutlet: PickupOutlet;
  price: number;

  constructor(deliveryService: DeliveryService) {
    super();
  }

  getCalendar(fromDate: Date, prevCalendar?: DeliveryCalendar) {
    return this.deliveryService.getVendorDeliveryCalendar(2, fromDate, prevCalendar);
  }

  confirmDelivery() {
    const { DELIVERY: type } = CART_DELIVERY_TYPES;
    const { THIRD_PARTY: deliveryType } = DELIVERY_ORDER_DELIVERY_TYPES;
    const { id: outlet } = this.pickupOutlet;
    const { price, priceWithoutVat, ...deliveryOrder } = this.deliveryOrder;

    this.onNext.emit({
      ...deliveryOrder,
      outlet, deliveryType, type,
      deliveryAgent: null,
      externalDeliveryFee: this.price
    });
  }
}

export class DeliveryToDoor extends DeliveryOrderBase {
  constructor(deliveryService: DeliveryService) {
    super();
  }

  getCalendar(fromDate: Date, prevCalendar?: DeliveryCalendar) {
    return this.deliveryService.getToDoorCalendar(2, fromDate, prevCalendar);
  }

  confirmDelivery() {
    const { COURIER: deliveryType } = DELIVERY_ORDER_DELIVERY_TYPES;
    const { DELIVERY: type } = CART_DELIVERY_TYPES;

    this.onNext.emit({
      ...this.deliveryOrder,
      outlet: null,
      externalDeliveryFee: 0,
      deliveryType, type
    });
  }
}
