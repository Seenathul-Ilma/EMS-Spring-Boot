const apiUrl = 'http://localhost:8080/api/v1/event';
//const apiUrl = 'https://eventms-49d40256cb77.herokuapp.com/api/v1/event';

$(document).ready(function() {
    generateNextEventId();
    loadAllEvents();

    function searchEvents() {
        const value = $("#search_event_input").val().toLowerCase();

        filteredEvents = allEvents.filter(event =>
            event.eventName.toLowerCase().includes(value) ||
            event.eventDescription.toLowerCase().includes(value) ||
            event.eventLocation.toLowerCase().includes(value) ||
            event.eventStatus.toLowerCase().includes(value)
        );

        if (filteredEvents.length === 0) {
            $('#event-card-container').html(`
                <div id="no-results" class="text-center text-muted mt-3">
                    <p>No matching events found.</p>
                </div>
            `);
            $('.pagination').empty(); // remove pagination if not found
        } else {
            currentPage = 1;
            renderPaginatedEvents(currentPage);
            generatePaginationButtons(filteredEvents.length);
        }
    }

    // to search events at live (while typing)
    $("#search_event_input").on("keyup", searchEvents);

    // to search events after click search btn
    $('#search_event_btn').on('click', function (e) {
        e.preventDefault();
        searchEvents();
    });

    $('#search_event_input').on('input', function () {
    if ($(this).val().trim() === '') {
        filteredEvents = []; // Reset filters
        renderPaginatedEvents(1); // Reset to page 1
        generatePaginationButtons(allEvents.length);
    }
});


});

let allEvents = [];
let filteredEvents = []; // add this next to allEvents

function generateNextEventId() {
    $.ajax({
        url: `${apiUrl}/all`,
        method: 'GET',
        success: function(response) {
            const nextId = response.length + 1;
            $('#eid').val(nextId);
        },
        error: function() {
            alert("Error..!\nFailed to generate next event Id..!")
        }
    });
}

function loadAllEvents() {
    $.ajax({
        url: `${apiUrl}/all`,
        method: 'GET',
        success: function(response) {
            //renderEventCards(response);
            //console.log(response);
            allEvents = response;
            filteredEvents = []; // reset filters
            renderPaginatedEvents(currentPage);
            generatePaginationButtons(response.length);
        },
        error: function() {
            alert("Error..!\nFailed to load events..!")
        }
    });
}

const itemsPerPage = 4;
let currentPage = 1;

function renderPaginatedEvents(page) {
    const dataToUse = filteredEvents.length ? filteredEvents : allEvents;

    const startIndex = (page - 1) * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;
    //const paginatedEvents = allEvents.slice(startIndex, endIndex);
    const paginatedEvents = dataToUse.slice(startIndex, endIndex);

    renderEventCards(paginatedEvents);
}

function renderEventCards(events) {
    const eventCardContainer = $('#event-card-container');
    eventCardContainer.empty();

    events.forEach(event => {
        const eventCard = $(`
            <div class="col event-card">
                <div class="card h-100">
                    <h6 class="card-header">${event.eventId}</h6>
                    <div class="card-body">
                        <h5 class="card-title">${event.eventName}</h5>
                        <p class="card-text mb-1">${event.eventDescription}</p>
                        <p class="card-text mb-0">Location: <small class="text-body-secondary">${event.eventLocation}</small></p>
                        <p class="card-text">Reserved On: <small class="text-body-secondary">${event.eventBookedDate}</small></p>
                        <p class="card-text">Held On: <small class="text-body-secondary">${event.eventDate}</small></p>
                        <p class="card-text">Status:
                        <span class="${event.eventStatus === 'Completed' ? 'badge text-bg-warning' : 'badge text-bg-success'}">${event.eventStatus}</span>
                        </p>
                    </div>
                    <div class="card-footer d-flex row g-2 text-center">
                        <button class="btn btn-sm btn-dark event_edit">
                            <i class="ti ti-edit fs-6"></i> Edit
                        </button>
                        <!--<button class="btn btn-sm btn-danger event_delete">
                            <i class="ti ti-trash fs-6"></i> Delete
                        </button>-->
                        <button class="btn btn-sm btn-danger event_status_change">
                            <i class="ti ti-trash fs-6"></i> Mark As Completed
                        </button>
                    </div>
                </div>
            </div>
        `);
        eventCard.data('event', event);
        eventCardContainer.append(eventCard);
    });
}

function generatePaginationButtons(totalCount) {
    const pageCount = Math.ceil(totalCount / itemsPerPage);
    const paginationContainer = $('.pagination');
    paginationContainer.empty();

    // Previous
    paginationContainer.append(`
        <li class="page-item">
            <a class="page-link bg-secondary text-white" href="#" aria-label="Previous" onclick="goToPage('prev')">
                <span aria-hidden="true">&laquo;</span>
            </a>
        </li>
    `);

    // Page numbers
    for (let i = 1; i <= pageCount; i++) {
        const isActive = (i === currentPage);
        paginationContainer.append(`
            <!-- li class="page-item ${isActive ? 'active' : ''}" -->
            <li class="page-item">
                <a class="page-link ${isActive ? 'bg-secondary text-white' : 'text-dark'}" 
                href="#" 
                onclick="goToPage(${i})">
                ${i}
                </a>
            </li>
        `);
    }

    // Next
    paginationContainer.append(`
        <li class="page-item">
            <a class="page-link bg-secondary text-white" href="#" aria-label="Next" onclick="goToPage('next')">
                <span aria-hidden="true">&raquo;</span>
            </a>
        </li>
    `);
}

function goToPage(page) {
    const dataToUse = filteredEvents.length ? filteredEvents : allEvents;
    const pageCount = Math.ceil(dataToUse.length / itemsPerPage);

    if (page === 'next') {
        if (currentPage < pageCount) currentPage++;
    } else if (page === 'prev') {
        if (currentPage > 1) currentPage--;
    } else {
        currentPage = page;
    }

    renderPaginatedEvents(currentPage);
    //generatePaginationButtons(allEvents.length); // Re-generate pagination buttons with updated currentPage
    generatePaginationButtons(dataToUse.length); // Re-generate pagination buttons with updated currentPage

}


$('#event-card-container').on('click', '.event-card, #event-edit', function () {
    const event = $(this).data('event');
    $('#eid').val(event.eventId);
    $('#ename').val(event.eventName);
    $('#edescription').val(event.eventDescription);
    $('#elocation').val(event.eventLocation);
    // Format date for input type="date"
    const eventDate = event.eventDate ? event.eventDate.split('T')[0] : '';
    $('#edate').val(eventDate);

    // Set the radio buttons for status according to event.eventStatus
    if(event.eventStatus === 'Scheduled') {
        $('#status-active').prop('checked', true);
    } else if(event.eventStatus === 'Completed') {
        $('#status-inactive').prop('checked', true);
    } else {
        // default or clear selection if needed
        $('input[name="estatus"]').prop('checked', false);
    }

    $('#event_form_fieldset').prop('disabled', false);
    $('#event_update').prop('disabled', false);
    $('#event_add').prop('disabled', true);
});

$('#getAllEvents').click(function () {
    loadAllEvents();
    $('#event_form_fieldset').prop('disabled', true);
});

$('#new_event_btn').click(function () {
    $('#event_form_fieldset').prop('disabled', false);
    $('#event_update').prop('disabled', true);
    $('#event_add').prop('disabled', false);
});

$('#event_reset').click(function () {
    // to get the accurate event id
    generateNextEventId();

    $('#ename').val("");
    $('#edate').val("");
    $('#elocation').val("");
    $('#edescription').val("");
    //$('#status-active').prop('checked', true);
    $('input[name="estatus"]').prop('checked', false);
});

$('#event_add').click(function () {

    const newEvent = {
        //eventId: $('#eid').val(),
        eventName: $('#ename').val(),
        eventDescription: $('#edescription').val(),
        eventLocation: $('#elocation').val(),
        eventDate: $('#edate').val(),
        eventBookedDate: new Date().toISOString().slice(0, 19),
        eventStatus: $('input[name="estatus"]:checked').val()
    };

    $.ajax({
        url: `${apiUrl}/create`,
        method: 'POST',
        data: JSON.stringify(newEvent),
        // Tell the server we are sending JSON
        contentType: 'application/json',
        success: function (response) {
            alert('Event saved successfully..!');
            console.log(response);
            //renderEventCards(response);
            loadAllEvents();
            generateNextEventId();
            $('#event_reset').click();
        },
        /*success: function () {
            alert('Data saved');
            // fetch all events again to update UI
            $('#getAllEvents').click();
        },*/
        error: function () {
            alert('Error Saving Event..!');
        }
    })
});


$('#event_update').click(function () {
    const selectedEvent = {
        eventId: $('#eid').val(),
        eventName: $('#ename').val(),
        eventDescription: $('#edescription').val(),
        eventLocation: $('#elocation').val(),
        eventDate: $('#edate').val(),
        eventBookedDate: new Date().toISOString().slice(0, 19),
        eventStatus: $('input[name="estatus"]:checked').val()
    };

    $.ajax({
        url: `${apiUrl}/edit`,
        method: 'PUT',
        data: JSON.stringify(selectedEvent),
        // Tell the server we are sending JSON
        contentType: 'application/json',
        success: function (response) {
            alert('Event updated successfully..!');
            console.log(response);
            //renderEventCards(response);
            loadAllEvents();
            //generateNextEventId();
            $('#event_reset').click();
            $('#event_update').prop('disabled', true);
        },
        /*success: function () {
            alert('Data saved');
            // fetch all events again to update UI
            $('#getAllEvents').click();
        },*/
        error: function () {
            alert('Failed to update event..!');
        }
    })
});


$('#event-card-container').on('click', '.event_status_change', function (e){
    e.stopPropagation();
    const event = $(this).closest('.event-card').data('event');
    const eventId = event.eventId;
    const isConfirm = confirm("Do you want to delete this event?");

    if(isConfirm) {
        $.ajax({
            //url: apiUrl,
            //url: "http://localhost:8080/App1_Web_exploded/event?eid"+eventId,
            //url: "http://localhost:8080/App1_Web_exploded/dbcp?eid"+eventId,
            url: `${apiUrl}/status/${eventId}`,
            method: 'PATCH',
            contentType: 'application/json',
            data: JSON.stringify({ eid: eventId }),
            success: function (response) {
                alert('Successfully changed event status..!');
                console.log(response);
                loadAllEvents();
                generateNextEventId();
                $('#event_reset').click();
            },
            error: function () {
                alert('Failed to change event status..!');
            }
        });
    }
});

$('#filter_status').on('change', function () {
    let status = $(this).val();
    console.log("Changed to:", status);

    if(status !== 'All Events') {
        $.ajax({
            //url: apiUrl,
            //url: "http://localhost:8080/App1_Web_exploded/event?eid"+eventId,
            //url: "http://localhost:8080/App1_Web_exploded/dbcp?eid"+eventId,
            url: `${apiUrl}/filter/${status}`,
            method: 'GET',
            success: function(response) {
                filteredEvents = response; // reset filters
                renderPaginatedEvents(currentPage);
                generatePaginationButtons(response.length);
            },
            error: function() {
                alert("Error..!\nFailed to filter events..!")
            }
        });
    } else {
        loadAllEvents();
    }

});
