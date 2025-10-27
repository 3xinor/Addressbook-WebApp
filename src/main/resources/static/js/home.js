var AddressBookPopup = {
    currentBookId: null,
    setup: function() {
        // handles click on addressbook links
        $(document).on('click', '#addressBooks a', AddressBookPopup.getBookInfo);

        // handles close button
        $(document).on('click', '#closePopup', AddressBookPopup.hidePopup);

        // handles creating a new addressbook
        $(document).on('click', '#createBookForm', AddressBookPopup.createBook);

        // handles edit button
        $(document).on('click', '#editPopup', AddressBookPopup.editPopup);
    },

    getBookInfo: function(event) {
        const apiUrl = $(this).data('api-url');
        if (!apiUrl) {
            // No API URL means fallback mechanism is used
            return true;
        }

        // JavaScript-enabled browser
        event.preventDefault();

        $.ajax({
            type: 'GET',
            url: apiUrl,
            dataType: 'json',
            timeout: 5000,
            success: AddressBookPopup.showPopup,
            error: function(xhr) {
                console.error('Error loading address book data:', xhr);
                alert('Error loading address book data!');
            }
        });
    },

    showPopup: function(book) {
        // Build popup HTML dynamically from json
        let html = `<h3>AddressBook #${book.id}</h3>`;
        AddressBookPopup.currentBookId = book.id;
        if (book.peoples && book.peoples.length > 0) {
            html += '<ul>';
            book.peoples.forEach(buddy => {
                html += `<li>${buddy.name} — ${buddy.phone}</li>`;
            });
            html += '</ul>';
        } else {
            html += '<p>No buddies in this address book.</p>';
        }
        html += '<a href="#" id="closePopup">Close</a> &nbsp; | &nbsp; ';
        html += '<a href="#" id="editPopup">Edit</a>';

        $('#popupContent').html(html);
        $('#addressBookPopup').fadeIn();
    },

    hidePopup: function(data) {
        $('#addressBookPopup').fadeOut();
    },

    createBook: function(event) {
        event.preventDefault(); // prevents default form POST

        const form = $(this);

        $.ajax({
            type: 'POST',
            url: form.attr('action'),
            success: function(newBook) {
                // server returns JSON of new book
                const viewUrl = '/view/addressbooks/' + encodeURIComponent(newBook.id);
                const apiUrl  = '/addressbooks/' + encodeURIComponent(newBook.id);

                const newListItem = `
                    <li>
                        <a href="${viewUrl}"
                           data-api-url="${apiUrl}">
                           AddressBook #${newBook.id}
                        </a>
                    </li>`;
                $('#addressBooks').append(newListItem);
            },

            error: function() {
                alert('Error creating new AddressBook!');
            }
        });
    },

    editPopup: function(event) {
        event.preventDefault()

        // determine which book is open
        const bookId = AddressBookPopup.currentBookId;
        if (!bookId) {
            alert("No address book selected!");
            return;
        }

        // fetch buddies
        $.ajax({
            type: 'GET',
            url: `/addressbooks/${bookId}`,
            dataType: 'json',
            success: function(buddies) {
                AddressBookPopup.showBuddyList(buddies);
            },
            error: function() {
                alert('Failed to load buddy list.');
            }
        });
    },

    showBuddyList: function(data) {
        // build HTML list from buddies array
        const buddies = data.peoples || [];

        // Update the heading with the AddressBook number
        $('#editPanel h3').text(`Edit AddressBook #${data.id}`);

        let html = '';
        if (buddies.length === 0) {
            html = '<p>No buddies yet.</p>';
        } else {
            buddies.forEach(buddy => {
                html += `
                    <div class="buddy-entry" data-id="${buddy.id}">
                      <strong>${buddy.name}</strong> — ${buddy.phone}
                      <button class="removeBuddy">Remove</button>
                    </div>`;});
        }

        // inject and show
        $('#buddyList').html(html);
        $('#editPanel').fadeIn();
        AddressBookPopup.hidePopup(data);

    },
};

$(document).on('submit', '#addBuddyForm', function(event) {
    event.preventDefault();
    const name = $('#buddyName').val();
    const phone = $('#buddyPhone').val();

    $.ajax({
        type: 'POST',
        url: `/addressbooks/${AddressBookPopup.currentBookId}/buddies`,
        contentType: 'application/json',
        data: JSON.stringify({name, phone}),
        success: function (newBuddies) {
            AddressBookPopup.showBuddyList(newBuddies);
        },
        error: function () {
            alert('Failed to update buddy list.');
        }
    });
});

$(document).on('click', '.removeBuddy', function() {
    const buddyDiv = $(this).closest('.buddy-entry');
    const buddyId = buddyDiv.data('id');
    $.ajax({
        type: 'DELETE',
        url: `/addressbooks/${AddressBookPopup.currentBookId}/buddies/${buddyId}`,
        success: function() {
            buddyDiv.remove();
        },
        error: function() {
            alert('Failed to remove buddy!');
        }
    });
});

$(AddressBookPopup.setup());
