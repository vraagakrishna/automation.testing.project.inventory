# Ndosi Test Automation Project 1: Inventory Form

## Assessment requirements

1. Login & Session

- [x] Valid credentials → Tabs visible, token exists (localStorage.authToken).
- [ ] ~~Invalid password → Alert text contains: Invalid credentials. Use: testuser / password123.~~ (The default
  credentials have been removed)
- [x] Extra spaces in credentials → Login still works (spaces are automatically trimmed).
- [x] Switch the Tab then back → Must login again (forced logout).
- [x] Click Logout → Token removed, login screen shown.

<br/>

2. Registration (if used)

- [x] Password mismatch → Alert: Passwords do not match!
- [x] Bad email format → Alert: Please enter a valid email address
- [x] Password < 8 → Alert: Password must be at least 8 characters long
- [x] Success → Alert includes Registration successful; login form shown with email filled.

<br/>

3. Wizard Step 1 Validation

- [ ] ~~No device → Select a device type~~ (Submit/Next button is disabled)
- [ ] ~~Device chosen, no brand → Select a brand~~ (Submit/Next button is disabled)
- [ ] ~~No storage → Choose storage size~~ (Submit/Next button is disabled)
- [ ] ~~Quantity 0 / blank → Quantity must be ≥ 1~~ (Submit/Next button is disabled)
- [x] Quantity 11 → Quantity must be ≤ 10
- [ ] ~~Address blank → Address required~~ (Submit/Next button is disabled)
- [x]  All corrected + Next → Step 2 shown.
- [x]  Error summary region appears when any error: Please correct highlighted fields.

<br/>

4. Step 1 Pricing Panel

- [x] No device+storage → Unit: —, Subtotal: —
- [x] Phone 64GB Qty1 → Unit R400.00 Subtotal R400.00
- [x] Phone 128GB Qty2 → Unit R480.00 Subtotal R960.00
- [x] Laptop 256GB Qty1 → Unit R1,360.00
- [x] Clear device → Pricing resets to —

<br/>

5. Step 2 Extras & Pricing

- [x] Shipping toggle Standard→Express → Shipping row R25.00 added.
- [x] Warranty None→1yr→2yr → Warranty row R0.00 / R49.00 / R89.00.
- [x] Formula check example (Phone 128GB Qty2 Express +1yr SAVE10): (R480*2)+R49+R25 = R1,034 → 10% = R103.40 → Total
  R930.60.

<br/>

6. Discount Codes (Single Item)

- [x] SAVE10 → Message: Code SAVE10 applied: -10%
- [x] SAVE20 after SAVE10 → Message updates: Code SAVE20 applied: -20%
- [x] random → Invalid code
- [x] Clear input + Apply → discount removed (no message)

<br/>

7. Add To Cart (Multi-Item)

- [x] Add valid Step2 item → Cart panel shows (Cart (1 item)).
- [x] Add different device → (Cart (2 items)).
- [x] First item discounted, second not → Only first shows discount line in its preview text.
- [x] Remove one → Item gone; Grand Total = sum remaining totals.
- [x] Remove last → Cart panel hidden.
- [ ] ~~Try Add with missing storage → Errors appear; cart count unchanged.~~ (Invalid; Submit/Next button is disabled)

<br/>

8. Cart Preview Panel (While on Step2)

- [x] Appears below pricing once ≥1 item added; remove there updates cart summary instantly.

<br/>

9. Review Cart Order Flow

- [x] Click Review Cart Order → Place Order / Cancel buttons + info box appear.
- [x] Click Cancel → Reverts to single Review Cart Order button.
- [x] Click Place Order → Success toast shows personalized message: [UserName], your order was purchased successfully!
- [x] Cart success toast → Shows detailed order info with user name, cart items, and grand total.
- [x] Success popup has dismiss button (×) and "View History" button → Manual control, no auto-timeout.
- [x] Automatic invoice generation → Creates invoice for cart order with all items and totals.
- [x] Double-click Place Order fast → Only one toast.

<br/>

10. Confirm Purchase (Single)

- [x] Single purchase → Success toast shows: [UserName], your order was purchased successfully!
- [x] Toast includes organized sections: Order Details box, Total amount, timestamp.
- [x] Success popup has dismiss button (×) in top-right corner → Manually dismissible, no auto-timeout.
- [x] Success popup has "View History" button in bottom-right → Expands invoice history and scrolls to section.
- [x] Automatic invoice generation → Creates invoice with unique ID, stores in history (last 10).
- [x] Cart already has items → Toast only current item; cart list unchanged.

<br/>

11. Invoice Generation & Management

- [x] Every purchase → Automatic invoice creation with unique sequential ID.
- [x] Invoice includes → Company logo, customer details, itemized list, totals, professional formatting.
- [x] Invoice storage → Saves complete data: customerName, customerEmail, deliveryAddress, items, total, htmlContent.
- [x] Invoice history panel → Shows last 10 invoices with view/download/delete options.
- [x] View invoice → Opens in new tab with HTML format matching design.
- [ ] Download PDF → Generates professional PDF with jsPDF, includes logo, **proper spacing, page boundaries**.
- [x] PDF Bill To section → Shows customer name, email, and delivery address.
- [ ] PDF layout → Company logo **top-left**, company info **top-right**, **proper margins and spacing**.
- [x] Delete invoice → Removes from history with confirmation, updates count display.
- [x] Clear all invoices → Bulk delete option with confirmation dialog.
- [x] Invoice counter → Shows "📄 Invoices (X)" with current count in collapsible header.

<br/>

12. Resets & Navigation

- [x] Confirm Purchase → Form back to defaults (Step1, cleared).
- [x] Place Order (cart) → Cart empty; confirm state reset.
- [x] Switch tab (e.g. API) then back → Wizard at Step1, cleared.

<br/>

13. Edge / Negative

- [x] Quantity dev-tools set 999 → Error Quantity must be ≤ 10.
- [x] Remove discounted item → Grand Total drops by its discounted total; no leftover discount.
- [x] Attempt Place Order with empty cart (after manual DOM tweak) → No toast (guard works).

<br/>

14. Multi-Device (2 Types) Cart

- [x] Add Phone (apple 64GB qty1, standard ship, no warranty, no discount) + Add To Cart → Cart (1).
- [x] Configure Laptop (macbook pro 256GB qty1, express ship, 1yr warranty, SAVE10) + Add To Cart → Cart (2).
- [x] Both different deviceType values (phone, laptop) appear — shows multi-type support.
- [x] Only laptop line shows discount + express shipping + warranty costs; phone line stays plain.
- [x] Grand Total = phone total + laptop total (add manually, matches to 2 decimals).
- [x] Remove laptop → Grand Total becomes just phone's total; no discount lines remain.
- [x] Remove phone instead → Discount line for laptop still present; math unchanged for laptop.
- [x] Edge: Quickly add phone then immediately laptop without revisiting Step1 for phone again → Earlier phone entry
  keeps its original color/storage (no bleed from laptop choices).

<br/>

15. Additional Coverage

- [x] Device change resets brand: pick phone+apple, switch to tablet → brand cleared, brand select disabled until
  re-picked.
- [x] Preview image vs SVG fallback: laptop/macbook uses laptop image, unknown brand would fallback to SVG (add a
  temporary brand via dev tools to observe).
- [x] Discount removal: apply SAVE10 then clear field + Apply → discount message disappears, pricing recalculates
  without discount.
- [x] Higher discount example: Use SAVE20 on laptop config; manually verify 20% math (show working).
- [x] 2yr warranty + express + SAVE10 combined math: verify subtotal + R89 + R25 − 10% lines equal displayed Total.
- [x] State isolation: Add discounted laptop to cart, configure new phone (no discount) → existing cart laptop keeps
  discount line.
- [x] Remove from preview list (Step2 small panel) updates main cart summary totals instantly (no stale total).
- [x] Single Confirm Purchase with discount: apply SAVE10 then Confirm Purchase (not cart) → toast includes discounted
  Total (no cart wording).
- [x] Double-click Place Order (cart) protection: rapid double click yields one toast and cart empties once.
- [x] Tab navigation reset: partially fill form, switch to API tab, return → form back to defaults (Step1, cleared).
- [x] Reload clears session: after login press refresh → must login again (token removed).
- [x] Aria-live announcements: trigger an error, then fix and submit; ensure screen reader (or inspect DOM) updates
  alert region.
- [x] Color persistence: change color to gold for phone, Add To Cart → new blank form defaults back to black (expected).
- [x] Reactive subtotal: change quantity from 1→2→3 on Step1; Subtotal updates each change without blur.
- [x] Precision check: choose config producing non-terminating decimal after discount (e.g. phone 128GB qty3 SAVE10) and
  verify rounded to 2 decimals correctly.
- [x] Cart quantity edit: use + / - or direct input to change an item's quantity (1–10); total and Grand Total recalc
  instantly; buttons disable at bounds.

<br/>

16. ~~Password Change~~ (There is no option to Change Password)

- [ ] All fields required → Error: All fields are required
- [ ] Password mismatch → Error: New passwords do not match
- [ ] Password too short → Error: New password must be at least 6 characters long
- [ ] Password visibility toggles → Eye icons work for all 3 password fields.
- [ ] Successful change → Success message for 3 seconds, form cleared.

<br/>

17. Accessibility Quick

- [x] Tab cycles through all actionable elements (login → tabs → form → cart buttons → invoice buttons).
- [x] Radio storage selectable by keyboard.
- [x] Error text uses role=alert; toast uses role=status.
- [x] Success popup dismiss button accessible via keyboard (Enter/Space). **alert.accept() is equivalent to pressing
  Enter**
- [x] Invoice history panel accessible with aria-labels and proper focus management.

<br/>

18. Invoice Testing Scenarios

- [x] Single item purchase → Invoice includes single line item with correct price calculations.
- [x] Multi-item cart → Invoice shows all items with individual and total calculations.
- [x] Discounted purchase → Invoice reflects discount in both HTML and PDF versions.
- [x] Express shipping + warranty → Invoice shows all additional charges correctly.
- [ ] PDF generation → Verify logo displays, customer info complete, **proper formatting**.
- [ ] PDF page boundaries → "Thank you" message and footer stay within **margins**.
- [x] Invoice sequence → Each new purchase gets incrementing invoice number.
- [x] History limit → Only last 10 invoices retained, oldest removed automatically.
- [x] View invoice → Opens in new tab with professional HTML layout.
- [x] Download PDF → File saves with correct name format "Invoice-XXXXX.pdf".
- [x] Delete invoice → Removes from history, updates counter, no errors.
- [x] Success popup "View History" → Expands history panel and scrolls smoothly.
- [x] Empty history → Shows appropriate "No invoices" message.
- [ ] Company logo → Displays in both HTML invoice and **PDF** (base64 embedded).
- [x] Customer data → Bill To section shows name, email, and delivery address.

<br/>

19. Exact Error Strings (Verify)

- [ ] ~~Select a device type~~ (Submit/Next button is disabled)
- [ ] ~~Select a brand~~ (Submit/Next button is disabled)
- [ ] ~~Choose storage size~~ (Submit/Next button is disabled)
- [ ] ~~Quantity must be ≥ 1~~ (Submit/Next button is disabled)
- [x] Quantity must be ≤ 10
- [ ] ~~Address required~~ (Submit/Next button is disabled)
- [x] Invalid code
- [x] Login alert contains Invalid login credentials
- [x] Error summary: Please correct highlighted fields.

<br/>

20. Pass Criteria: All expectations met, totals math correct to 2 decimals, no console errors.

<br/>

<br/>

## Additional requirements

- [x] Use **Page Object Model (POM)** framework
- [x] Generate a report
- [x] Take screenshots
- [ ] Read from Excel
- [ ] Implement CI/CD pipeline using GitHub actions

