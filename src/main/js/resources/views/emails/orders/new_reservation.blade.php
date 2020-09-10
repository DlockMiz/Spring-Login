@component('mail::message')
Order #{{$obj->id}} Confirmation<br>
<br>
First Name: {{$obj->first_name}}<br>
Last Name: {{$obj->last_name}}<br>
Phone Number: {{$obj->phone}}<br>
Email: {{$obj->email}}<br><br>
Address: {{$obj->address}}<br>
City: {{$obj->city}}<br>
State: {{$obj->state}}<br>
Zip Code: {{$obj->zip_code}}<br><br>
Site Type: {{$obj->site_type_text}}<br>
Date/Time In: {{$obj->reservation_date_start}}<br>
Date/Time Out: {{$obj->reservation_date_end}}<br>
Length of Stay: {{$obj->length_of_stay_text}}<br>
Vehicle License: {{$obj->vehicle_license}}<br>
Passport America? {{$obj->passport_america_member}} / (If Yes): {{$obj->membership_id}}<br>
Hear about us: {{$obj->hear_about_us}}<br><br>
Site Spot ID: {{$obj->site_spot_id}}<br>
Site Name: {{$obj->site_spot_name}}<br>
Site Price: ${{$obj->total_site_price}}<br>
@if($obj->discount)
	Passport Discount: - ${{$obj->discount}}<br>
@endif
Internet Service Fee: ${{$obj->internet_service_fee}}<br>
Tax: ${{$obj->tax}}<br>
Total Price: ${{$obj->total_price}}<br>


@endcomponent
