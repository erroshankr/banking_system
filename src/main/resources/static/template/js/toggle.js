$(document).ready(function() {


$('[data-toggle="collapse"]').click(function() {
  $(this).toggleClass( "active" );
  if ($(this).hasClass("active")) {
    $(this).text("Hide Balance");
  } else {
    $(this).text("Show Balance");
  }
});
});
