// Было:
export class UnifiedOrderInfoTableComponent implements OnInit {
  @Input() order: IOrder;
  @Input() isCustomerView: boolean;

  orderLines: IOrderLine[];
  invoiceFeeOrderLine: IOrderLine = null;
  deliveryOrder: IOrderDelivery;
  deliveryPrice: number;

  ngOnInit() {
    const { multiOrder: { orderLines: multiOrderLines }, orderLines } = this.order;
    this.invoiceFeeOrderLine = orderLines.find(l => l.type === CART_LINE_TYPES.INVOICE_FEE) || null;

    if (this.isCustomerView) {
      const cakeItEasyInvoiceFee = multiOrderLines.find(l => l.type === CART_LINE_TYPES.INVOICE_FEE) || null;
      this.invoiceFeeOrderLine = this.invoiceFeeOrderLine || cakeItEasyInvoiceFee;
    }

    const { GIFT, THIRD_PARTY } = DELIVERY_ORDER_DELIVERY_TYPES;
    const { DELIVERY_DEPOSIT, THIRD_PARTY_DELIVERY_FEE } = CART_LINE_TYPES;
    const { deliveryOrder: { deliveryType, price: deliveryOrderPrice } } = this.order;

    this.deliveryPrice = deliveryOrderPrice;

    if (deliveryType === GIFT && this.isCustomerView) {
      this.deliveryPrice = this.getLinePriceByType(DELIVERY_DEPOSIT, orderLines) || deliveryOrderPrice;
    }

    if (deliveryType === THIRD_PARTY) {
      this.deliveryPrice = this.getLinePriceByType(THIRD_PARTY_DELIVERY_FEE, orderLines);
    }

    const { INVOICE_FEE, REFUND, DISCOUNT } = CART_LINE_TYPES;
    const excludedTypes = [INVOICE_FEE, REFUND, DISCOUNT, THIRD_PARTY_DELIVERY_FEE];

    this.orderLines = orderLines.filter(({ type }) => excludedTypes.indexOf(type) === -1);
  }

  private getLinePriceByType(type: string, lines: IOrderLine[]): number {
    return lines
      .filter(l => l.type === type)
      .reduce((acc, l) => (acc = acc + l.price, acc), 0);
  }
}

// Стало:
export class UnifiedOrderInfoTableComponent implements OnInit {
  @Input() order: IOrder;
  @Input() isCustomerView: boolean;

  orderLines: IOrderLine[];
  invoiceFeeOrderLine: IOrderLine = null;
  deliveryOrder: IOrderDelivery;
  deliveryPrice: number;

  ngOnInit() {
    this.deliveryPrice = this.deliveryOrder ? this._getDeliveryPrice() : null;
    this.orderLines = this.filterOrderLines(this.order.orderLines);

    this.invoiceFeeOrderLine = this._getInvoiceFeeOrderLine();
  }

  private _getDeliveryPrice() {
    const { GIFT, THIRD_PARTY } = DELIVERY_ORDER_DELIVERY_TYPES;
    const { DELIVERY_DEPOSIT, THIRD_PARTY_DELIVERY_FEE } = CART_LINE_TYPES;
    const { multiOrder: { orderLines }, deliveryOrder: { deliveryType, price: deliveryOrderPrice } } = this.order;

    if (deliveryType === GIFT && this.isCustomerView) {
      return this.getLinePriceByType(DELIVERY_DEPOSIT, orderLines) || deliveryOrderPrice;
    }

    if (deliveryType === THIRD_PARTY) {
      return this.getLinePriceByType(THIRD_PARTY_DELIVERY_FEE, orderLines);
    }

    return deliveryOrderPrice;
  }

  private getLinePriceByType(type: string, lines: IOrderLine[]): number {
    return lines
      .filter(l => l.type === type)
      .reduce((acc, l) => (acc = acc + l.price, acc), 0);
  }

  private filterOrderLines(orderLines: IOrderLine[]): IOrderLine[] {
    const { INVOICE_FEE, REFUND, DISCOUNT, THIRD_PARTY_DELIVERY_FEE } = CART_LINE_TYPES;
    const excludedTypes = [INVOICE_FEE, REFUND, DISCOUNT, THIRD_PARTY_DELIVERY_FEE];

    return orderLines.filter(({ type }) => excludedTypes.indexOf(type) === -1);
  }

  private _getInvoiceFeeOrderLine() {
    const { multiOrder: { orderLines: multiOrderLines }, orderLines } = this.order;
    const bakeryInvoiceFee = orderLines.find(l => l.type === CART_LINE_TYPES.INVOICE_FEE) || null;

    if (this.isCustomerView) {
      const cakeItEasyInvoiceFee = multiOrderLines.find(l => l.type === CART_LINE_TYPES.INVOICE_FEE) || null;
      return bakeryInvoiceFee || cakeItEasyInvoiceFee;
    }

    return bakeryInvoiceFee;
  }
}
