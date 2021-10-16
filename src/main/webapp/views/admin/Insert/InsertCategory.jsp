<div class="page-header">
    <h1 class="page-heading">Categories</h1>
</div>
<section>
    <div class="row mb-5">
        <div class="col-lg-4">
            <div class="card mb-4 mb-lg-0">
                <div class="card-body">
                    <div class="mb-4">
                        <label class="form-label" for="categoryName">Name</label>
                        <input class="form-control" id="categoryName" type="text">
                        <div class="form-text">The name is how it appears on your site.</div>
                    </div>
                    <div class="mb-4">
                        <label class="form-label" for="categorySlug">Slug</label>
                        <input class="form-control" id="categorySlug" type="text">
                        <div class="form-text">The “slug” is the URL-friendly version of the name. It is usually all lowercase and contains only letters, numbers, and hyphens.</div>
                    </div>
                    <div class="mb-4">
                        <label class="form-label" for="categoryParent">Parent</label>
                        <select class="form-select" id="categoryParent" name="categoryParent">
                            <option value="0">None</option>
                            <option value="0">Gear</option>
                            <option value="1">Stories</option>
                            <option value="2">Tips &amp; Tricks</option>
                            <option value="3">Trips</option>
                            <option value="4">Gear</option>
                            <option value="5">Stories</option>
                            <option value="6">Tips &amp; Tricks</option>
                            <option value="7">Trips</option>
                            <option value="8">Uncategorized</option>
                        </select>
                        <div class="form-text">Categories, unlike tags, can have a hierarchy. You might have a Jazz category, and under that have children categories for Bebop and Big Band. Totally optional.</div>
                    </div>
                    <div class="mb-4">
                        <label class="form-label" for="categoryDescription">Parent</label>
                        <textarea class="form-control" id="categoryDescription" name="categoryDescription"></textarea>
                        <div class="form-text">The description is not prominent by default; however, some themes may show it.</div>
                    </div>
                    <button class="btn btn-primary mb-4">Add New Category</button>
                </div>
            </div>
        </div>
        <div class="col-lg-8">
            <div class="card card-table">
                <div class="preload-wrapper">
                    <table class="table table-hover mb-0" id="categoryDatatable">
                        <thead>
                        <tr>
                            <th style="width: 20px;"> </th>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Slug</th>
                            <th>Count</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td><span class="form-check"><input class="form-check-input" type="checkbox"></span></td>
                            <td><a href="javascript:void(0)" class="text-decoration-none text-reset fw-bolder">Gear</a></td>
                            <td>-</td>
                            <td>gear</td>
                            <td class="text-end"><a href="cms-post.html" class="text-decoration-none">23</a></td>
                        </tr>
                        <tr>
                            <td><span class="form-check"><input class="form-check-input" type="checkbox"></span></td>
                            <td><a href="javascript:void(0)" class="text-decoration-none text-reset fw-bolder">Stories</a></td>
                            <td>-</td>
                            <td>stories</td>
                            <td class="text-end"><a href="cms-post.html" class="text-decoration-none">2</a></td>
                        </tr>
                        <tr>
                            <td><span class="form-check"><input class="form-check-input" type="checkbox"></span></td>
                            <td><a href="javascript:void(0)" class="text-decoration-none text-reset fw-bolder">Tips &amp; Tricks</a></td>
                            <td>-</td>
                            <td>tips-&amp; tricks</td>
                            <td class="text-end"><a href="cms-post.html" class="text-decoration-none">4</a></td>
                        </tr>
                        <tr>
                            <td><span class="form-check"><input class="form-check-input" type="checkbox"></span></td>
                            <td><a href="javascript:void(0)" class="text-decoration-none text-reset fw-bolder">Trips</a></td>
                            <td>-</td>
                            <td>trips</td>
                            <td class="text-end"><a href="cms-post.html" class="text-decoration-none">5</a></td>
                        </tr>
                        <tr>
                            <td><span class="form-check"><input class="form-check-input" type="checkbox"></span></td>
                            <td><a href="javascript:void(0)" class="text-decoration-none text-reset fw-bolder">Gear</a></td>
                            <td>-</td>
                            <td>gear</td>
                            <td class="text-end"><a href="cms-post.html" class="text-decoration-none">23</a></td>
                        </tr>
                        <tr>
                            <td><span class="form-check"><input class="form-check-input" type="checkbox"></span></td>
                            <td><a href="javascript:void(0)" class="text-decoration-none text-reset fw-bolder">Stories</a></td>
                            <td>-</td>
                            <td>stories</td>
                            <td class="text-end"><a href="cms-post.html" class="text-decoration-none">2</a></td>
                        </tr>
                        <tr>
                            <td><span class="form-check"><input class="form-check-input" type="checkbox"></span></td>
                            <td><a href="javascript:void(0)" class="text-decoration-none text-reset fw-bolder">Tips &amp; Tricks</a></td>
                            <td>-</td>
                            <td>tips-&amp; tricks</td>
                            <td class="text-end"><a href="cms-post.html" class="text-decoration-none">4</a></td>
                        </tr>
                        <tr>
                            <td><span class="form-check"><input class="form-check-input" type="checkbox"></span></td>
                            <td><a href="javascript:void(0)" class="text-decoration-none text-reset fw-bolder">Trips</a></td>
                            <td>-</td>
                            <td>trips</td>
                            <td class="text-end"><a href="cms-post.html" class="text-decoration-none">5</a></td>
                        </tr>
                        <tr>
                            <td><span class="form-check"><input class="form-check-input" type="checkbox"></span></td>
                            <td><a href="javascript:void(0)" class="text-decoration-none text-reset fw-bolder">Uncategorized</a></td>
                            <td>-</td>
                            <td>uncategorized</td>
                            <td class="text-end"><a href="cms-post.html" class="text-decoration-none">2</a></td>
                        </tr>
                        </tbody>
                    </table><span class="me-2" id="categoryBulkAction">
                      <select class="form-select form-select-sm d-inline w-auto mb-1 mb-lg-0" name="categoryBulkAction">
                        <option>Bulk Actions</option>
                        <option>Delete</option>
                      </select>
                      <button class="btn btn-sm btn-outline-primary align-top mb-1 mb-lg-0">Apply</button></span>
                </div>
            </div>
        </div>
    </div>
</section>