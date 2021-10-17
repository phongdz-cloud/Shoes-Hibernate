<div class="page-header">
    <h1 class="page-heading">Product Name</h1>
    <ul class="list-inline text-sm">
        <li class="list-inline-item"><a class="text-gray-600" href="#!"><i class="fas fa-copy me-2"> </i>Duplicate</a></li>
        <li class="list-inline-item"><a class="text-gray-600" href="#!"><i class="fas fa-download me-2"> </i>Export</a></li>
    </ul>
</div>
<section>
    <div class="row mb-5">
        <div class="col-lg-8 col-xxl-9 mb-4 mb-lg-0">
            <div class="card mb-4">
                <div class="card-header">
                    <div class="card-heading">Main Info</div>
                </div>
                <div class="card-body">
                    <label class="form-label" >Title</label>
                    <input class="form-control mb-4" type="text">
                    <label class="form-label" >Title</label>
                    <input class="form-control mb-4"  type="text">
                    <label class="form-label" >Title</label>
                    <input class="form-control mb-4"  type="text">
                    <label class="form-label" >Title</label>
                    <input class="form-control mb-4"  type="text">
                    <button class="btn btn-outline-primary mb-4">Add Media</button>
                </div>
            </div>
            <div class="card mb-4">
                <div class="card-header">
                    <div class="card-heading">Prices & Stock </div>
                </div>
                <div class="card-body">
                    <div class="row gy-3">
                        <div class="col-12">
                            <label class="form-label fw-bold">Main Price</label>
                            <div class="input-group">
                                <div class="input-group-text">$</div>
                                <input class="form-control">
                            </div>
                        </div>
                        <div class="col-12 col-lg-6 text-sm">
                            <label class="form-label text-muted">Regular Price</label>
                            <div class="form-check form-switch float-end">
                                <label class="form-check-label text-sm sr-only" for="displayRegular">Display Regular price</label>
                                <input class="form-check-input" id="displayRegular" type="checkbox">
                            </div>
                            <div class="input-group">
                                <div class="input-group-text">$ </div>
                                <input class="form-control">
                            </div>
                        </div>
                        <div class="col-12 col-lg-6 text-sm">
                            <label class="form-label text-muted">Discounted Price</label>
                            <div class="form-check form-switch float-end">
                                <label class="form-check-label text-sm sr-only" for="displayDiscounted">Display Discounted price</label>
                                <input class="form-check-input" id="displayDiscounted" type="checkbox">
                            </div>
                            <div class="input-group">
                                <div class="input-group-text">$ </div>
                                <input class="form-control">
                            </div>
                        </div>
                    </div>
                    <hr class="bg-gray-500 my-4">
                    <label class="form-label fw-bold">Items in stock</label>
                    <input class="form-control">
                </div>
            </div>
        </div>
        <div class="col-lg-4 col-xxl-3">
            <div class="card shadow-sm mb-4">
                <div class="card-header py-4">
                    <h4 class="card-heading">Categorization</h4>
                </div>
                <div class="card-body">
                    <label class="form-label">Category</label>
                    <select class="form-select">
                        <option value="category0"> Gear</option>
                        <option value="category1"> Stories</option>
                        <option value="category2"> Tips &amp; Tricks</option>
                        <option value="category3"> Trips</option>
                        <option value="category4"> Uncategorized</option>
                    </select>
                    <hr class="bg-gray-500 my-4">
                    <label class="form-label">Brand</label>
                    <select class="form-select">
                        <option value="brand0"> Memora</option>
                    </select>
                    <hr class="bg-gray-500 my-4">
                    <label class="form-label">Collection</label>
                    <select class="form-select">
                        <option value="">Not Set</option>
                        <option value="collection0"> Spring</option>
                        <option value="collection1"> Summer</option>
                    </select>
                    <hr class="bg-gray-500 my-4">
                    <label class="form-label">Tags</label>
                    <input class="form-control" id="tags" type="text" value="Tag 1, Tag 2" placeholder="Enter something">
                </div>
            </div>
            <div class="card shadow-sm mb-4">
                <div class="card-header py-4">
                    <h4 class="card-heading">Format</h4>
                </div>
                <div class="card-body">
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="format" id="format0" checked>
                        <label class="form-check-label" for="format0">Standard</label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="format" id="format1">
                        <label class="form-check-label" for="format1">Aside</label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="format" id="format2">
                        <label class="form-check-label" for="format2">Image</label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="format" id="format3">
                        <label class="form-check-label" for="format3">Video</label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="format" id="format4">
                        <label class="form-check-label" for="format4">Quote</label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="format" id="format5">
                        <label class="form-check-label" for="format5">Link</label>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>