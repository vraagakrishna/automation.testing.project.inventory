# Ndosi Test Automation Project 1: Inventory Form

## Assessment requirements

1. Login & Session

- [ ] Valid credentials → Tabs visible, token exists (localStorage.authToken).
- [ ] Invalid password → Alert text contains: Invalid credentials. Use: testuser / password123.
- [ ] Extra spaces in credentials → Login still works (spaces are automatically trimmed).
- [ ] Switch the Tab then back → Must login again (forced logout).
- [ ] Click Logout → Token removed, login screen shown.

<br/>

2. Registration (if used)

- [ ] Password mismatch → Alert: Passwords do not match!
- [ ] Bad email format → Alert: Please enter a valid email address
- [ ] Password < 8 → Alert: Password must be at least 8 characters long
- [ ] Success → Alert includes Registration successful; login form shown with email filled.

<br/>

3. Wizard Step 1 Validation

- [ ] No device → Select a device type
- [ ] Device chosen, no brand → Select a brand
- [ ] No storage → Choose storage size
- [ ] Quantity 0 / blank → Quantity must be ≥ 1
- [ ] Quantity 11 → Quantity must be ≤ 10
- [ ] Address blank → Address required
- [ ]  All corrected + Next → Step 2 shown.
- [ ]  Error summary region appears when any error: Please correct highlighted fields.

<br/>

4. Step 1 Pricing Panel

- [ ] No device+storage → Unit: —, Subtotal: —
- [ ] Phone 64GB Qty1 → Unit R400.00 Subtotal R400.00
- [ ] Phone 128GB Qty2 → Unit R480.00 Subtotal R960.00
- [ ] Laptop 256GB Qty1 → Unit R1,360.00
- [ ] Clear device → Pricing resets to —

<br/>

5. Step 2 Extras & Pricing

- [ ] Shipping toggle Standard→Express → Shipping row R25.00 added.
- [ ] Warranty None→1yr→2yr → Warranty row R0.00 / R49.00 / R89.00.
- [ ] Formula check example (Phone 128GB Qty2 Express +1yr SAVE10): (R480*2)+R49+R25 = R1,034 → 10% = R103.40 → Total
  R930.60.

<br/>

6. Discount Codes (Single Item)

- [ ] SAVE10 → Message: Code SAVE10 applied: -10%
- [ ] SAVE20 after SAVE10 → Message updates: Code SAVE20 applied: -20%
- [ ] random → Invalid code
- [ ] Clear input + Apply → discount removed (no message)

<br/>

7. Add To Cart (Multi-Item)

- [ ] Add valid Step2 item → Cart panel shows (Cart (1 item)).
- [ ] Add different device → (Cart (2 items)).
- [ ] First item discounted, second not → Only first shows discount line in its preview text.
- [ ] Remove one → Item gone; Grand Total = sum remaining totals.
- [ ] Remove last → Cart panel hidden.
- [ ] Try Add with missing storage → Errors appear; cart count unchanged.

<br/>

8. Cart Preview Panel (While on Step2)

- [ ] Appears below pricing once ≥1 item added; remove there updates cart summary instantly.

<br/>

9. Review Cart Order Flow

- [ ] Click Review Cart Order → Place Order / Cancel buttons + info box appear.
- [ ] Click Cancel → Reverts to single Review Cart Order button.
- [ ] Click Place Order → Success toast shows personalized message: [UserName], your order was purchased successfully!
- [ ] Cart success toast → Shows detailed order info with user name, cart items, and grand total.
- [ ] Success popup has dismiss button (×) and "View History" button → Manual control, no auto-timeout.
- [ ] Automatic invoice generation → Creates invoice for cart order with all items and totals.
- [ ] Double-click Place Order fast → Only one toast.

<br/>

10. Confirm Purchase (Single)

- [ ] Single purchase → Success toast shows: [UserName], your order was purchased successfully!
- [ ] Toast includes organized sections: Order Details box, Total amount, timestamp.
- [ ] Success popup has dismiss button (×) in top-right corner → Manually dismissible, no auto-timeout.
- [ ] Success popup has "View History" button in bottom-right → Expands invoice history and scrolls to section.
- [ ] Automatic invoice generation → Creates invoice with unique ID, stores in history (last 10).
- [ ] Cart already has items → Toast only current item; cart list unchanged.

<br/>

11. Invoice Generation & Management

- [ ] Every purchase → Automatic invoice creation with unique sequential ID.
- [ ] Invoice includes → Company logo, customer details, itemized list, totals, professional formatting.
- [ ] Invoice storage → Saves complete data: customerName, customerEmail, deliveryAddress, items, total, htmlContent.
- [ ] Invoice history panel → Shows last 10 invoices with view/download/delete options.
- [ ] View invoice → Opens in new tab with HTML format matching design.
- [ ] Download PDF → Generates professional PDF with jsPDF, includes logo, proper spacing, page boundaries.
- [ ] PDF Bill To section → Shows customer name, email, and delivery address.
- [ ] PDF layout → Company logo top-left, company info top-right, proper margins and spacing.  (but order??)
- [ ] Delete invoice → Removes from history with confirmation, updates count display.
- [ ] Clear all invoices → Bulk delete option with confirmation dialog.
- [ ] Invoice counter → Shows "📄 Invoices (X)" with current count in collapsible header.

<br/>

12. Resets & Navigation

- [ ] Confirm Purchase → Form back to defaults (Step1, cleared).
- [ ] Place Order (cart) → Cart empty; confirm state reset.
- [ ] Switch tab (e.g. API) then back → Wizard at Step1, cleared.

<br/>

13. Edge / Negative

- [ ] Quantity dev-tools set 999 → Error Quantity must be ≤ 10.
- [ ] Remove discounted item → Grand Total drops by its discounted total; no leftover discount.
- [ ] Attempt Place Order with empty cart (after manual DOM tweak) → No toast (guard works).

<br/>

14. Multi-Device (2 Types) Cart

- [ ] Add Phone (apple 64GB qty1, standard ship, no warranty, no discount) + Add To Cart → Cart (1).
- [ ] Configure Laptop (macbook pro 256GB qty1, express ship, 1yr warranty, SAVE10) + Add To Cart → Cart (2).
- [ ] Both different deviceType values (phone, laptop) appear — shows multi-type support.
- [ ] Only laptop line shows discount + express shipping + warranty costs; phone line stays plain.
- [ ] Grand Total = phone total + laptop total (add manually, matches to 2 decimals).
- [ ] Remove laptop → Grand Total becomes just phone's total; no discount lines remain.
- [ ] Remove phone instead → Discount line for laptop still present; math unchanged for laptop.
- [ ] Edge: Quickly add phone then immediately laptop without revisiting Step1 for phone again → Earlier phone entry
  keeps its original color/storage (no bleed from laptop choices).

<br/>

15. Additional Coverage

- [ ] Device change resets brand: pick phone+apple, switch to tablet → brand cleared, brand select disabled until
  re-picked.
- [ ] Preview image vs SVG fallback: laptop/macbook uses laptop image, unknown brand would fallback to SVG (add a
  temporary brand via dev tools to observe).
- [ ] Discount removal: apply SAVE10 then clear field + Apply → discount message disappears, pricing recalculates
  without discount.
- [ ] Higher discount example: Use SAVE20 on laptop config; manually verify 20% math (show working).
- [ ] 2yr warranty + express + SAVE10 combined math: verify subtotal + R89 + R25 − 10% lines equal displayed Total.
- [ ] State isolation: Add discounted laptop to cart, configure new phone (no discount) → existing cart laptop keeps
  discount line.
- [ ] Remove from preview list (Step2 small panel) updates main cart summary totals instantly (no stale total).
- [ ] Single Confirm Purchase with discount: apply SAVE10 then Confirm Purchase (not cart) → toast includes discounted
  Total (no cart wording).
- [ ] Double-click Place Order (cart) protection: rapid double click yields one toast and cart empties once.
- [ ] Tab navigation reset: partially fill form, switch to API tab, return → form back to defaults (Step1, cleared).
- [ ] Reload clears session: after login press refresh → must login again (token removed).
- [ ] Aria-live announcements: trigger an error, then fix and submit; ensure screen reader (or inspect DOM) updates
  alert region.
- [ ] Color persistence: change color to gold for phone, Add To Cart → new blank form defaults back to black (expected).
- [ ] Reactive subtotal: change quantity from 1→2→3 on Step1; Subtotal updates each change without blur.
- [ ] Precision check: choose config producing non-terminating decimal after discount (e.g. phone 128GB qty3 SAVE10) and
  verify rounded to 2 decimals correctly.
- [ ] Cart quantity edit: use + / - or direct input to change an item's quantity (1–10); total and Grand Total recalc
  instantly; buttons disable at bounds.

<br/>

16. Password Change

- [ ] All fields required → Error: All fields are required
- [ ] Password mismatch → Error: New passwords do not match
- [ ] Password too short → Error: New password must be at least 6 characters long
- [ ] Password visibility toggles → Eye icons work for all 3 password fields.
- [ ] Successful change → Success message for 3 seconds, form cleared.

<br/>

17. Accessibility Quick

- [ ] Tab cycles through all actionable elements (login → tabs → form → cart buttons → invoice buttons).
- [ ] Radio storage selectable by keyboard.
- [ ] Error text uses role=alert; toast uses role=status.
- [ ] Success popup dismiss button accessible via keyboard (Enter/Space).
- [ ] Invoice history panel accessible with aria-labels and proper focus management.

<br/>

18. Invoice Testing Scenarios

- [ ] Single item purchase → Invoice includes single line item with correct price calculations.
- [ ] Multi-item cart → Invoice shows all items with individual and total calculations.
- [ ] Discounted purchase → Invoice reflects discount in both HTML and PDF versions.
- [ ] Express shipping + warranty → Invoice shows all additional charges correctly.
- [ ] PDF generation → Verify logo displays, customer info complete, proper formatting. ?
- [ ] PDF page boundaries → "Thank you" message and footer stay within margins. ?
- [ ] Invoice sequence → Each new purchase gets incrementing invoice number.
- [ ] History limit → Only last 10 invoices retained, oldest removed automatically.
- [ ] View invoice → Opens in new tab with professional HTML layout.
- [ ] Download PDF → File saves with correct name format "Invoice-XXXXX.pdf".
- [ ] Delete invoice → Removes from history, updates counter, no errors.
- [ ] Success popup "View History" → Expands history panel and scrolls smoothly.
- [ ] Empty history → Shows appropriate "No invoices" message.
- [ ] Company logo → Displays in both HTML invoice and PDF (base64 embedded). --> ?
- [ ] Customer data → Bill To section shows name, email, and delivery address.

<br/>

19. Exact Error Strings (Verify)

- [ ] Select a device type
- [ ] Select a brand
- [ ] Choose storage size
- [ ] Quantity must be ≥ 1
- [ ] Quantity must be ≤ 10
- [ ] Address required
- [ ] Invalid code
- [ ] Login alert contains Invalid login credentials
- [ ] Error summary: Please correct highlighted fields.

<br/>

20. Pass Criteria: All expectations met, totals math correct to 2 decimals, no console errors.

<br/>
